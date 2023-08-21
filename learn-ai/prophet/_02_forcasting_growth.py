import pandas as pd
from prophet import Prophet

if __name__ == '__main__':
    """
    预测增长
    https://facebook.github.io/prophet/docs/saturating_forecasts.html#forecasting-growth
    """
    df = pd.read_csv('https://raw.githubusercontent.com/facebook/prophet/main/examples/example_wp_log_R.csv')

    # 指定承载能力cap，但这通常是使用有关市场规模的数据或专业知识来设置的。
    # 注意的重要事项是cap必须为数据框中的每一行指定，并且它不必是常量
    # 如果市场规模在增长，那么cap可以是递增序列
    df['cap'] = 8.5

    # 指定逻辑增长
    m = Prophet(growth='logistic')
    m.fit(df)

    # 创建预测 future， 预测未来 5 年
    future = m.make_future_dataframe(periods=1826)
    # 必须指定未来的容量，在这里，我们将容量保持在与历史相同的值
    future['cap'] = 8.5

    # 开始预测，并展示图表
    fcst = m.predict(future)
    fig = m.plot(fcst).show()


    ### 饱和最小值 ###
    # 改造数据
    df['y'] = 10 - df['y']

    # 最大值
    df['cap'] = 6
    # 最小值
    df['floor'] = 1.5

    # 最大值
    future['cap'] = 6
    # 最小值
    future['floor'] = 1.5
    m = Prophet(growth='logistic')
    m.fit(df)
    fcst = m.predict(future)
    m.plot(fcst).show()