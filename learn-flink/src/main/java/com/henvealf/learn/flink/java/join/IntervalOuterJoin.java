package com.henvealf.learn.flink.java.join;

import org.apache.flink.annotation.PublicEvolving;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.Utils;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.UnsupportedTimeCharacteristicException;
import org.apache.flink.streaming.api.functions.co.ProcessJoinFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Preconditions;

import static org.apache.flink.util.Preconditions.checkNotNull;

/**
 * Perform a left join over a time interval.
 *
 * @param <T1> The type parameter of the elements in the first streams
 * @param <T2> The type parameter of the elements in the second stream
 */
public class IntervalOuterJoin<T1, T2, KEY> {

    private final KeyedStream<T1, KEY> streamOne;
    private final KeyedStream<T2, KEY> streamTwo;

    enum JoinType {
        LEFT,
        RIGHT,
        OUTER,
    }

    /**
     * The time behaviour enum defines how the system determines time for time-dependent order
     * and operations that depend on time.
     */
    enum TimeBehaviour {
        ProcessingTime,
        EventTime
    }

    /**
     * The time behaviour to specify processing time or event time. Default time behaviour is
     * {@link TimeBehaviour#EventTime}.
     */
    private TimeBehaviour timeBehaviour = TimeBehaviour.EventTime;

    /**
     * 关联类型
     */
    private JoinType joinType;

    IntervalOuterJoin(KeyedStream<T1, KEY> streamOne,
                      KeyedStream<T2, KEY> streamTwo, JoinType joinType) {
        this.streamOne = checkNotNull(streamOne);
        this.streamTwo = checkNotNull(streamTwo);
        this.joinType = joinType;
    }

    /** Sets the time characteristic to event time. */
    public IntervalOuterJoin<T1, T2, KEY> inEventTime() {
        timeBehaviour = TimeBehaviour.EventTime;
        return this;
    }

    /** Sets the time characteristic to processing time. */
    public IntervalOuterJoin<T1, T2, KEY> inProcessingTime() {
        timeBehaviour = TimeBehaviour.ProcessingTime;
        return this;
    }

    /**
     * Specifies the time boundaries over which the join operation works, so that
     *
     * <pre>
     * leftElement.timestamp + lowerBound <= rightElement.timestamp <= leftElement.timestamp + upperBound
     * </pre>
     *
     * <p>By default both the lower and the upper bound are inclusive. This can be configured
     * with {@link IntervalLeftJoined#lowerBoundExclusive()} and {@link
     * IntervalLeftJoined#upperBoundExclusive()}
     *
     * @param lowerBound The lower bound. Needs to be smaller than or equal to the upperBound
     * @param upperBound The upper bound. Needs to be bigger than or equal to the lowerBound
     */
    @PublicEvolving
    public IntervalLeftJoined<T1, T2, KEY> between(Time lowerBound, Time upperBound) {
        if (timeBehaviour != TimeBehaviour.EventTime) {
            throw new UnsupportedTimeCharacteristicException(
                    "Time-bounded stream joins are only supported in event time");
        }

        checkNotNull(lowerBound, "A lower bound needs to be provided for a time-bounded join");
        checkNotNull(upperBound, "An upper bound needs to be provided for a time-bounded join");

        return new IntervalLeftJoined<>(
                streamOne,
                streamTwo,
                lowerBound.toMilliseconds(),
                upperBound.toMilliseconds(),
                true,
                true,
                joinType);
    }

    /**
     * IntervalLeftJoined is a container for two streams that have keys for both sides as well as the
     * time boundaries over which elements should be left-joined.
     *
     * @param <IN1> Input type of elements from the first stream
     * @param <IN2> Input type of elements from the second stream
     * @param <KEY> The type of the key
     */
    @PublicEvolving
    public static class IntervalLeftJoined<IN1, IN2, KEY> {

        private final KeyedStream<IN1, KEY> left;
        private final KeyedStream<IN2, KEY> right;

        private final long lowerBound;
        private final long upperBound;

        private final KeySelector<IN1, KEY> keySelector1;
        private final KeySelector<IN2, KEY> keySelector2;

        private boolean lowerBoundInclusive;
        private boolean upperBoundInclusive;

        private JoinType joinType;

        public IntervalLeftJoined(
                KeyedStream<IN1, KEY> left,
                KeyedStream<IN2, KEY> right,
                long lowerBound,
                long upperBound,
                boolean lowerBoundInclusive,
                boolean upperBoundInclusive,
                JoinType joinType) {

            this.left = checkNotNull(left);
            this.right = checkNotNull(right);

            this.lowerBound = lowerBound;
            this.upperBound = upperBound;

            this.lowerBoundInclusive = lowerBoundInclusive;
            this.upperBoundInclusive = upperBoundInclusive;

            this.keySelector1 = left.getKeySelector();
            this.keySelector2 = right.getKeySelector();

            this.joinType = joinType;
        }

        /** Set the upper bound to be exclusive. */
        @PublicEvolving
        public IntervalLeftJoined<IN1, IN2, KEY> upperBoundExclusive() {
            this.upperBoundInclusive = false;
            return this;
        }

        /** Set the lower bound to be exclusive. */
        @PublicEvolving
        public IntervalLeftJoined<IN1, IN2, KEY> lowerBoundExclusive() {
            this.lowerBoundInclusive = false;
            return this;
        }

        /**
         * Completes the join operation with the given user function that is executed for each
         * joined pair of elements.
         *
         * @param processJoinFunction The user-defined process join function.
         * @param <OUT> The output type.
         * @return The transformed {@link DataStream}.
         */
        @PublicEvolving
        public <OUT> SingleOutputStreamOperator<OUT> process(
                ProcessJoinFunction<IN1, IN2, OUT> processJoinFunction) {
            Preconditions.checkNotNull(processJoinFunction);

            final TypeInformation<OUT> outputType =
                    TypeExtractor.getBinaryOperatorReturnType(
                            processJoinFunction,
                            ProcessJoinFunction.class,
                            0,
                            1,
                            2,
                            TypeExtractor.NO_INDEX,
                            left.getType(),
                            right.getType(),
                            Utils.getCallLocationName(),
                            true);

            return process(processJoinFunction, outputType);
        }

        /**
         * Completes the join operation with the given user function that is executed for each
         * joined pair of elements. This methods allows for passing explicit type information for
         * the output type.
         *
         * @param processJoinFunction The user-defined process join function.
         * @param outputType The type information for the output type.
         * @param <OUT> The output type.
         * @return The transformed {@link DataStream}.
         */
        @PublicEvolving
        public <OUT> SingleOutputStreamOperator<OUT> process(
                ProcessJoinFunction<IN1, IN2, OUT> processJoinFunction,
                TypeInformation<OUT> outputType) {
            Preconditions.checkNotNull(processJoinFunction);
            Preconditions.checkNotNull(outputType);

            final ProcessJoinFunction<IN1, IN2, OUT> cleanedUdf =
                    left.getExecutionEnvironment().clean(processJoinFunction);

            final IntervalOuterJoinOperator<KEY, IN1, IN2, OUT> operator =
                    new IntervalOuterJoinOperator<>(
                            lowerBound,
                            upperBound,
                            lowerBoundInclusive,
                            upperBoundInclusive,
                            left.getType().createSerializer(left.getExecutionConfig()),
                            right.getType().createSerializer(right.getExecutionConfig()),
                            cleanedUdf,
                            joinType);

            return left.connect(right)
                    .keyBy(keySelector1, keySelector2)
                    .transform("Interval Join", outputType, operator);
        }
    }
}