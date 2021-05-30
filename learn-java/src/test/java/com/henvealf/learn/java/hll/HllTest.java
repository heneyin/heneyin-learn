package com.henvealf.learn.java.hll;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import net.agkn.hll.HLL;
import org.junit.Test;

import java.util.stream.LongStream;

/**
 * @author hongliang.yin/Henvealf
 * @date 2020/9/16
 */
public class HllTest {
    long numberOfElements = 1_000_00;

    /**
     * 1 万:
     * log2m: 10, regWidth: 5, 相差: 0.4400%, took: 121, length: 643
     * log2m: 10, regWidth: 6, 相差: 0.4400%, took: 5, length: 771
     * log2m: 10, regWidth: 7, 相差: 0.4400%, took: 4, length: 899
     * log2m: 10, regWidth: 8, 相差: 0.4400%, took: 2, length: 1027
     * --------------------------------------------------------------------------------------------
     * log2m: 11, regWidth: 5, 相差: 0.8200%, took: 2, length: 1283
     * log2m: 11, regWidth: 6, 相差: 0.8200%, took: 2, length: 1539
     * log2m: 11, regWidth: 7, 相差: 0.8200%, took: 3, length: 1795
     * log2m: 11, regWidth: 8, 相差: 0.8200%, took: 1, length: 2051
     * --------------------------------------------------------------------------------------------
     * log2m: 12, regWidth: 5, 相差: 0.0600%, took: 2, length: 2563
     * log2m: 12, regWidth: 6, 相差: 0.0600%, took: 2, length: 3075
     * log2m: 12, regWidth: 7, 相差: 0.0600%, took: 2, length: 3587
     * log2m: 12, regWidth: 8, 相差: 0.0600%, took: 3, length: 4099
     * --------------------------------------------------------------------------------------------
     * log2m: 13, regWidth: 5, 相差: 0.5800%, took: 2, length: 5123
     * log2m: 13, regWidth: 6, 相差: 0.5800%, took: 2, length: 6147
     * log2m: 13, regWidth: 7, 相差: 0.5800%, took: 3, length: 7171
     * log2m: 13, regWidth: 8, 相差: 0.5800%, took: 3, length: 8195
     * --------------------------------------------------------------------------------------------
     * log2m: 14, regWidth: 5, 相差: 0.9300%, took: 3, length: 10243
     * log2m: 14, regWidth: 6, 相差: 0.9300%, took: 3, length: 12291
     * log2m: 14, regWidth: 7, 相差: 0.9300%, took: 3, length: 14339
     * log2m: 14, regWidth: 8, 相差: 0.9300%, took: 4, length: 16387
     * --------------------------------------------------------------------------------------------
     * log2m: 15, regWidth: 5, 相差: 0.0400%, took: 5, length: 20483
     * log2m: 15, regWidth: 6, 相差: 0.0400%, took: 4, length: 24579
     * log2m: 15, regWidth: 7, 相差: 0.0400%, took: 3, length: 28675
     * log2m: 15, regWidth: 8, 相差: 0.0400%, took: 3, length: 32771
     * --------------------------------------------------------------------------------------------
     * log2m: 16, regWidth: 5, 相差: 0.3500%, took: 4, length: 40963
     * log2m: 16, regWidth: 6, 相差: 0.3500%, took: 5, length: 25589
     * log2m: 16, regWidth: 7, 相差: 0.3500%, took: 3, length: 26752
     * log2m: 16, regWidth: 8, 相差: 0.3500%, took: 3, length: 27915
     * --------------------------------------------------------------------------------------------
     * log2m: 17, regWidth: 5, 相差: 0.0000%, took: 2, length: 80003
     * log2m: 17, regWidth: 6, 相差: 0.0000%, took: 2, length: 80003
     * log2m: 17, regWidth: 7, 相差: 0.0000%, took: 2, length: 80003
     * log2m: 17, regWidth: 8, 相差: 0.0000%, took: 1, length: 80003
     * --------------------------------------------------------------------------------------------
     * log2m: 18, regWidth: 5, 相差: 0.0000%, took: 2, length: 80003
     * log2m: 18, regWidth: 6, 相差: 0.0000%, took: 2, length: 80003
     * log2m: 18, regWidth: 7, 相差: 0.0000%, took: 2, length: 80003
     * log2m: 18, regWidth: 8, 相差: 0.0000%, took: 1, length: 80003
     * --------------------------------------------------------------------------------------------
     * log2m: 19, regWidth: 5, 相差: 0.0000%, took: 2, length: 80003
     * log2m: 19, regWidth: 6, 相差: 0.0000%, took: 1, length: 80003
     * log2m: 19, regWidth: 7, 相差: 0.0000%, took: 2, length: 80003
     * log2m: 19, regWidth: 8, 相差: 0.0000%, took: 1, length: 80003
     * --------------------------------------------------------------------------------------------
     * log2m: 20, regWidth: 5, 相差: 0.0000%, took: 2, length: 80003
     * log2m: 20, regWidth: 6, 相差: 0.0000%, took: 2, length: 80003
     * log2m: 20, regWidth: 7, 相差: 0.0000%, took: 1, length: 80003
     * log2m: 20, regWidth: 8, 相差: 0.0000%, took: 2, length: 80003
     * --------------------------------------------------------------------------------------------
     *
     * 10 万：
     * log2m: 10, regWidth: 5, 相差: 1.1940%, took: 184, length: 643
     * log2m: 10, regWidth: 6, 相差: 1.1940%, took: 14, length: 771
     * log2m: 10, regWidth: 7, 相差: 1.1940%, took: 13, length: 899
     * log2m: 10, regWidth: 8, 相差: 1.1940%, took: 12, length: 1027
     * --------------------------------------------------------------------------------------------
     * log2m: 11, regWidth: 5, 相差: 3.3360%, took: 14, length: 1283
     * log2m: 11, regWidth: 6, 相差: 3.3360%, took: 14, length: 1539
     * log2m: 11, regWidth: 7, 相差: 3.3360%, took: 20, length: 1795
     * log2m: 11, regWidth: 8, 相差: 3.3360%, took: 11, length: 2051
     * --------------------------------------------------------------------------------------------
     * log2m: 12, regWidth: 5, 相差: 1.6110%, took: 8, length: 2563
     * log2m: 12, regWidth: 6, 相差: 1.6110%, took: 8, length: 3075
     * log2m: 12, regWidth: 7, 相差: 1.6110%, took: 8, length: 3587
     * log2m: 12, regWidth: 8, 相差: 1.6110%, took: 7, length: 4099
     * --------------------------------------------------------------------------------------------
     * log2m: 13, regWidth: 5, 相差: 1.1850%, took: 7, length: 5123
     * log2m: 13, regWidth: 6, 相差: 1.1850%, took: 7, length: 6147
     * log2m: 13, regWidth: 7, 相差: 1.1850%, took: 9, length: 7171
     * log2m: 13, regWidth: 8, 相差: 1.1850%, took: 6, length: 8195
     * --------------------------------------------------------------------------------------------
     * log2m: 14, regWidth: 5, 相差: 0.0190%, took: 8, length: 10243
     * log2m: 14, regWidth: 6, 相差: 0.0190%, took: 8, length: 12291
     * log2m: 14, regWidth: 7, 相差: 0.0190%, took: 7, length: 14339
     * log2m: 14, regWidth: 8, 相差: 0.0190%, took: 8, length: 16387
     * --------------------------------------------------------------------------------------------
     * log2m: 15, regWidth: 5, 相差: 1.4260%, took: 8, length: 20483
     * log2m: 15, regWidth: 6, 相差: 1.4260%, took: 11, length: 24579
     * log2m: 15, regWidth: 7, 相差: 1.4260%, took: 13, length: 28675
     * log2m: 15, regWidth: 8, 相差: 1.4260%, took: 12, length: 32771
     * --------------------------------------------------------------------------------------------
     * log2m: 16, regWidth: 5, 相差: 0.6540%, took: 10, length: 40963
     * log2m: 16, regWidth: 6, 相差: 0.6540%, took: 13, length: 49155
     * log2m: 16, regWidth: 7, 相差: 0.6540%, took: 12, length: 57347
     * log2m: 16, regWidth: 8, 相差: 0.6540%, took: 13, length: 65539
     * --------------------------------------------------------------------------------------------
     * log2m: 17, regWidth: 5, 相差: 0.0890%, took: 10, length: 81923
     * log2m: 17, regWidth: 6, 相差: 0.0890%, took: 15, length: 98307
     * log2m: 17, regWidth: 7, 相差: 0.0890%, took: 11, length: 114691
     * log2m: 17, regWidth: 8, 相差: 0.0890%, took: 11, length: 131075
     * --------------------------------------------------------------------------------------------
     * log2m: 18, regWidth: 5, 相差: 0.0260%, took: 11, length: 163843
     * log2m: 18, regWidth: 6, 相差: 0.0260%, took: 14, length: 196611
     * log2m: 18, regWidth: 7, 相差: 0.0260%, took: 14, length: 229379
     * log2m: 18, regWidth: 8, 相差: 0.0260%, took: 14, length: 262147
     * --------------------------------------------------------------------------------------------
     * log2m: 19, regWidth: 5, 相差: 0.0860%, took: 20, length: 327683
     * log2m: 19, regWidth: 6, 相差: 0.0860%, took: 29, length: 393219
     * log2m: 19, regWidth: 7, 相差: 0.0860%, took: 28, length: 295656
     * log2m: 19, regWidth: 8, 相差: 0.0860%, took: 24, length: 307027
     * --------------------------------------------------------------------------------------------
     * log2m: 20, regWidth: 5, 相差: 0.1290%, took: 32, length: 297697
     * log2m: 20, regWidth: 6, 相差: 0.1290%, took: 40, length: 309605
     * log2m: 20, regWidth: 7, 相差: 0.0000%, took: 12, length: 800003
     * log2m: 20, regWidth: 8, 相差: 0.0000%, took: 13, length: 800003
     * --------------------------------------------------------------------------------------------
     *
     * 100 万:
     * log2m: 10, regWidth: 5, 相差: 1.6332%, took: 255, length: 643
     * log2m: 10, regWidth: 6, 相差: 1.6332%, took: 66, length: 771
     * log2m: 10, regWidth: 7, 相差: 1.6332%, took: 54, length: 899
     * log2m: 10, regWidth: 8, 相差: 1.6332%, took: 60, length: 1027
     * --------------------------------------------------------------------------------------------
     * log2m: 11, regWidth: 5, 相差: 0.4736%, took: 96, length: 1283
     * log2m: 11, regWidth: 6, 相差: 0.4736%, took: 52, length: 1539
     * log2m: 11, regWidth: 7, 相差: 0.4736%, took: 49, length: 1795
     * log2m: 11, regWidth: 8, 相差: 0.4736%, took: 58, length: 2051
     * --------------------------------------------------------------------------------------------
     * log2m: 12, regWidth: 5, 相差: 0.4929%, took: 126, length: 2563
     * log2m: 12, regWidth: 6, 相差: 0.4929%, took: 70, length: 3075
     * log2m: 12, regWidth: 7, 相差: 0.4929%, took: 54, length: 3587
     * log2m: 12, regWidth: 8, 相差: 0.4929%, took: 56, length: 4099
     * --------------------------------------------------------------------------------------------
     * log2m: 13, regWidth: 5, 相差: 0.6487%, took: 56, length: 5123
     * log2m: 13, regWidth: 6, 相差: 0.6487%, took: 48, length: 6147
     * log2m: 13, regWidth: 7, 相差: 0.6487%, took: 54, length: 7171
     * log2m: 13, regWidth: 8, 相差: 0.6487%, took: 50, length: 8195
     * --------------------------------------------------------------------------------------------
     * log2m: 14, regWidth: 5, 相差: 0.3245%, took: 48, length: 10243
     * log2m: 14, regWidth: 6, 相差: 0.3245%, took: 66, length: 12291
     * log2m: 14, regWidth: 7, 相差: 0.3245%, took: 64, length: 14339
     * log2m: 14, regWidth: 8, 相差: 0.3245%, took: 51, length: 16387
     * --------------------------------------------------------------------------------------------
     * log2m: 15, regWidth: 5, 相差: 0.2898%, took: 58, length: 20483
     * log2m: 15, regWidth: 6, 相差: 0.2898%, took: 57, length: 24579
     * log2m: 15, regWidth: 7, 相差: 0.2898%, took: 55, length: 28675
     * log2m: 15, regWidth: 8, 相差: 0.2898%, took: 54, length: 32771
     * --------------------------------------------------------------------------------------------
     * log2m: 16, regWidth: 5, 相差: 0.3683%, took: 55, length: 40963
     * log2m: 16, regWidth: 6, 相差: 0.3683%, took: 56, length: 49155
     * log2m: 16, regWidth: 7, 相差: 0.3683%, took: 59, length: 57347
     * log2m: 16, regWidth: 8, 相差: 0.3683%, took: 57, length: 65539
     * --------------------------------------------------------------------------------------------
     * log2m: 17, regWidth: 5, 相差: 0.2327%, took: 61, length: 81923
     * log2m: 17, regWidth: 6, 相差: 0.2327%, took: 72, length: 98307
     * log2m: 17, regWidth: 7, 相差: 0.2327%, took: 62, length: 114691
     * log2m: 17, regWidth: 8, 相差: 0.2327%, took: 57, length: 131075
     * --------------------------------------------------------------------------------------------
     * log2m: 18, regWidth: 5, 相差: 0.4676%, took: 58, length: 163843
     * log2m: 18, regWidth: 6, 相差: 0.4676%, took: 61, length: 196611
     * log2m: 18, regWidth: 7, 相差: 0.4676%, took: 61, length: 229379
     * log2m: 18, regWidth: 8, 相差: 0.4676%, took: 61, length: 262147
     * --------------------------------------------------------------------------------------------
     * log2m: 19, regWidth: 5, 相差: 0.1984%, took: 67, length: 327683
     * log2m: 19, regWidth: 6, 相差: 0.1984%, took: 71, length: 393219
     * log2m: 19, regWidth: 7, 相差: 0.1984%, took: 73, length: 458755
     * log2m: 19, regWidth: 8, 相差: 0.1984%, took: 73, length: 524291
     * --------------------------------------------------------------------------------------------
     * log2m: 20, regWidth: 5, 相差: 0.1549%, took: 75, length: 655363
     * log2m: 20, regWidth: 6, 相差: 0.1549%, took: 76, length: 786435
     * log2m: 20, regWidth: 7, 相差: 0.1549%, took: 93, length: 917507
     * log2m: 20, regWidth: 8, 相差: 0.1549%, took: 89, length: 1048579
     * --------------------------------------------------------------------------------------------
     *
     * 1000万
     * log2m: 10, regWidth: 5, 相差: 6.5350%, took: 828, length: 643
     * log2m: 10, regWidth: 6, 相差: 6.5350%, took: 721, length: 771
     * log2m: 10, regWidth: 7, 相差: 6.5350%, took: 535, length: 899
     * log2m: 10, regWidth: 8, 相差: 6.5350%, took: 533, length: 1027
     * --------------------------------------------------------------------------------------------
     * log2m: 11, regWidth: 5, 相差: 3.4355%, took: 514, length: 1283
     * log2m: 11, regWidth: 6, 相差: 3.4355%, took: 460, length: 1539
     * log2m: 11, regWidth: 7, 相差: 3.4355%, took: 463, length: 1795
     * log2m: 11, regWidth: 8, 相差: 3.4355%, took: 452, length: 2051
     * --------------------------------------------------------------------------------------------
     * log2m: 12, regWidth: 5, 相差: 1.8222%, took: 462, length: 2563
     * log2m: 12, regWidth: 6, 相差: 1.8222%, took: 459, length: 3075
     * log2m: 12, regWidth: 7, 相差: 1.8222%, took: 464, length: 3587
     * log2m: 12, regWidth: 8, 相差: 1.8222%, took: 455, length: 4099
     * --------------------------------------------------------------------------------------------
     * log2m: 13, regWidth: 5, 相差: 0.9040%, took: 468, length: 5123
     * log2m: 13, regWidth: 6, 相差: 0.9040%, took: 466, length: 6147
     * log2m: 13, regWidth: 7, 相差: 0.9040%, took: 485, length: 7171
     * log2m: 13, regWidth: 8, 相差: 0.9040%, took: 481, length: 8195
     * --------------------------------------------------------------------------------------------
     * log2m: 14, regWidth: 5, 相差: 0.4938%, took: 504, length: 10243
     * log2m: 14, regWidth: 6, 相差: 0.4938%, took: 468, length: 12291
     * log2m: 14, regWidth: 7, 相差: 0.4938%, took: 493, length: 14339
     * log2m: 14, regWidth: 8, 相差: 0.4938%, took: 463, length: 16387
     * --------------------------------------------------------------------------------------------
     * log2m: 15, regWidth: 5, 相差: 0.2135%, took: 486, length: 20483
     * log2m: 15, regWidth: 6, 相差: 0.2135%, took: 468, length: 24579
     * log2m: 15, regWidth: 7, 相差: 0.2135%, took: 476, length: 28675
     * log2m: 15, regWidth: 8, 相差: 0.2135%, took: 465, length: 32771
     * --------------------------------------------------------------------------------------------
     * log2m: 16, regWidth: 5, 相差: 0.6675%, took: 474, length: 40963
     * log2m: 16, regWidth: 6, 相差: 0.6675%, took: 476, length: 49155
     * log2m: 16, regWidth: 7, 相差: 0.6675%, took: 480, length: 57347
     * log2m: 16, regWidth: 8, 相差: 0.6675%, took: 477, length: 65539
     * --------------------------------------------------------------------------------------------
     * log2m: 17, regWidth: 5, 相差: 0.1544%, took: 485, length: 81923
     * log2m: 17, regWidth: 6, 相差: 0.1544%, took: 494, length: 98307
     * log2m: 17, regWidth: 7, 相差: 0.1544%, took: 501, length: 114691
     * log2m: 17, regWidth: 8, 相差: 0.1544%, took: 498, length: 131075
     * --------------------------------------------------------------------------------------------
     * log2m: 18, regWidth: 5, 相差: 0.0130%, took: 511, length: 163843
     * log2m: 18, regWidth: 6, 相差: 0.0130%, took: 522, length: 196611
     * log2m: 18, regWidth: 7, 相差: 0.0130%, took: 526, length: 229379
     * log2m: 18, regWidth: 8, 相差: 0.0130%, took: 522, length: 262147
     * --------------------------------------------------------------------------------------------
     * log2m: 19, regWidth: 5, 相差: 0.1499%, took: 545, length: 327683
     * log2m: 19, regWidth: 6, 相差: 0.1499%, took: 548, length: 393219
     * log2m: 19, regWidth: 7, 相差: 0.1499%, took: 588, length: 458755
     * log2m: 19, regWidth: 8, 相差: 0.1499%, took: 565, length: 524291
     * --------------------------------------------------------------------------------------------
     * log2m: 20, regWidth: 5, 相差: 0.0448%, took: 644, length: 655363
     * log2m: 20, regWidth: 6, 相差: 0.0448%, took: 655, length: 786435
     * log2m: 20, regWidth: 7, 相差: 0.0448%, took: 870, length: 917507
     * log2m: 20, regWidth: 8, 相差: 0.0448%, took: 689, length: 1048579
     * --------------------------------------------------------------------------------------------
     *
     * 一亿:
     * log2m: 10, regWidth: 5, 相差: 0.8522%, took: 6414, length: 643
     * log2m: 10, regWidth: 6, 相差: 0.8522%, took: 4892, length: 771
     * log2m: 10, regWidth: 7, 相差: 0.8522%, took: 4926, length: 899
     * log2m: 10, regWidth: 8, 相差: 0.8522%, took: 6038, length: 1027
     * --------------------------------------------------------------------------------------------
     * log2m: 11, regWidth: 5, 相差: 3.0965%, took: 5619, length: 1283
     * log2m: 11, regWidth: 6, 相差: 3.0965%, took: 5123, length: 1539
     * log2m: 11, regWidth: 7, 相差: 3.0965%, took: 6679, length: 1795
     * log2m: 11, regWidth: 8, 相差: 3.0965%, took: 4983, length: 2051
     * --------------------------------------------------------------------------------------------
     * log2m: 12, regWidth: 5, 相差: 0.1861%, took: 4858, length: 2563
     * log2m: 12, regWidth: 6, 相差: 0.1861%, took: 4753, length: 3075
     * log2m: 12, regWidth: 7, 相差: 0.1861%, took: 4801, length: 3587
     * log2m: 12, regWidth: 8, 相差: 0.1861%, took: 4723, length: 4099
     * --------------------------------------------------------------------------------------------
     * log2m: 13, regWidth: 5, 相差: 1.2816%, took: 4953, length: 5123
     * log2m: 13, regWidth: 6, 相差: 1.2816%, took: 4801, length: 6147
     * log2m: 13, regWidth: 7, 相差: 1.2816%, took: 4839, length: 7171
     * log2m: 13, regWidth: 8, 相差: 1.2816%, took: 4582, length: 8195
     * --------------------------------------------------------------------------------------------
     * log2m: 14, regWidth: 5, 相差: 0.6541%, took: 5034, length: 10243
     * log2m: 14, regWidth: 6, 相差: 0.6541%, took: 4904, length: 12291
     * log2m: 14, regWidth: 7, 相差: 0.6541%, took: 4790, length: 14339
     * log2m: 14, regWidth: 8, 相差: 0.6541%, took: 4638, length: 16387
     * --------------------------------------------------------------------------------------------
     * log2m: 15, regWidth: 5, 相差: 0.9528%, took: 4857, length: 20483
     * log2m: 15, regWidth: 6, 相差: 0.9528%, took: 4728, length: 24579
     * log2m: 15, regWidth: 7, 相差: 0.9528%, took: 4731, length: 28675
     * log2m: 15, regWidth: 8, 相差: 0.9528%, took: 4638, length: 32771
     * --------------------------------------------------------------------------------------------
     * log2m: 16, regWidth: 5, 相差: 0.5086%, took: 4719, length: 40963
     * log2m: 16, regWidth: 6, 相差: 0.5086%, took: 4780, length: 49155
     * log2m: 16, regWidth: 7, 相差: 0.5086%, took: 4799, length: 57347
     * log2m: 16, regWidth: 8, 相差: 0.5086%, took: 4754, length: 65539
     * --------------------------------------------------------------------------------------------
     * log2m: 17, regWidth: 5, 相差: 0.2223%, took: 4804, length: 81923
     * log2m: 17, regWidth: 6, 相差: 0.2223%, took: 4884, length: 98307
     * log2m: 17, regWidth: 7, 相差: 0.2223%, took: 4923, length: 114691
     * log2m: 17, regWidth: 8, 相差: 0.2223%, took: 4905, length: 131075
     * --------------------------------------------------------------------------------------------
     * log2m: 18, regWidth: 5, 相差: 0.1061%, took: 5507, length: 163843
     * log2m: 18, regWidth: 6, 相差: 0.1061%, took: 5299, length: 196611
     * log2m: 18, regWidth: 7, 相差: 0.1061%, took: 5231, length: 229379
     * log2m: 18, regWidth: 8, 相差: 0.1061%, took: 5071, length: 262147
     * --------------------------------------------------------------------------------------------
     * log2m: 19, regWidth: 5, 相差: 0.0921%, took: 5437, length: 327683
     * log2m: 19, regWidth: 6, 相差: 0.0921%, took: 5557, length: 393219
     * log2m: 19, regWidth: 7, 相差: 0.0921%, took: 5370, length: 458755
     * log2m: 19, regWidth: 8, 相差: 0.0921%, took: 5353, length: 524291
     * --------------------------------------------------------------------------------------------
     * log2m: 20, regWidth: 5, 相差: 0.0161%, took: 5517, length: 655363
     * log2m: 20, regWidth: 6, 相差: 0.0161%, took: 5971, length: 786435
     * log2m: 20, regWidth: 7, 相差: 0.0161%, took: 5945, length: 917507
     * log2m: 20, regWidth: 8, 相差: 0.0161%, took: 5609, length: 1048579
     * --------------------------------------------------------------------------------------------
     */
    @Test
    public void test() {

        HashFunction hashFunction = Hashing.murmur3_128();
        long toleratedDifference = 5_000_00;

        for (int i = 10; i <= 20 ; i++) {
            for (int j = 5; j <= 8; j++) {
                long start = System.currentTimeMillis();
                HLL hll = new HLL(i, j);
                LongStream.range(0, numberOfElements).forEach(element -> {
                            long hashedValue = hashFunction.newHasher().putLong(element).hash().asLong();
                            hll.addRaw(hashedValue);
                        }
                );
                long used = System.currentTimeMillis() - start;
                long cardinality = hll.cardinality();
                System.out.println(String.format("log2m: %s, regWidth: %s, 相差: %.4f%%, took: %s, length: %s",
                        i, j, (double)Math.abs(numberOfElements - cardinality) / numberOfElements * 100, used, hll.toBytes().length));
            }
            System.out.println("--------------------------------------------------------------------------------------------");
        }
    }

    @Test
    public void test1() {
        System.out.println(numberOfElements - 99995752);
    }

    /**
     * 相同大小 registers 的合并
     */
    @Test
    public void test_merge_on_same_registers() {
        HashFunction hashFunction = Hashing.murmur3_128();

        HLL hll1 = new HLL(14, 6);
        hll1.addRaw(1);
        hll1.addRaw(2);
        hll1.addRaw(2);
        hll1.addRaw(3);
        hll1.addRaw(4);

        long cardinality1 = hll1.cardinality();
        System.out.println("1: " + cardinality1);

        HLL hll2 = new HLL(14, 6);
        hll2.addRaw(2);
        hll2.addRaw(3);
        hll2.addRaw(3);
        hll2.addRaw(4);
        hll2.addRaw(4);
        hll2.addRaw(5);

        long cardinality2 = hll2.cardinality();
        System.out.println("2: " + cardinality2);

        hll1.union(hll2);
        System.out.println("1+2: " + hll1.cardinality());
    }

    /**
     * 不同大小 registers 的合并
     */
    @Test
    public void test_merge_on_not_same_registers() {

        HLL hll1 = new HLL(14, 6);
        hll1.addRaw(1);
        hll1.addRaw(2);
        hll1.addRaw(2);
        hll1.addRaw(3);
        hll1.addRaw(4);

        long cardinality1 = hll1.cardinality();
        System.out.println("1: " + cardinality1);

        HLL hll2 = new HLL(15, 7);
        hll2.addRaw(2);
        hll2.addRaw(3);
        hll2.addRaw(3);
        hll2.addRaw(4);
        hll2.addRaw(4);
        hll2.addRaw(5);

        long cardinality2 = hll2.cardinality();
        System.out.println("2: " + cardinality2);

        hll1.union(hll2);
        System.out.println("1+2: " + hll1.cardinality());
    }

    /**
     *
     */
    @Test
    public void test_byte_to_String_and_in_json() {
        HLL hll1 = new HLL(14, 6);
        HashFunction hashFunction = Hashing.murmur3_128();
        LongStream.range(0, numberOfElements).forEach(element -> {
                    long hashedValue = hashFunction.newHasher().putLong(element).hash().asLong();
                    hll1.addRaw(hashedValue);
                }
        );
        hll1.addRaw(1);
        hll1.addRaw(2);
        hll1.addRaw(2);
        hll1.addRaw(3);
        hll1.addRaw(4);

        long cardinality1 = hll1.cardinality();
        System.out.println("1: " + cardinality1);
        byte[] bytes = hll1.toBytes();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test", bytes);

        String jsonStr = jsonObject.toString();
        System.out.println("jsonStr: " + jsonStr);

        JSONObject replayJson = JSON.parseObject(jsonStr);
        byte[] replayBytes = replayJson.getBytes("test");
        HLL hll2 = HLL.fromBytes(replayBytes);

        System.out.println("replay: " + hll2.cardinality());

    }
}
