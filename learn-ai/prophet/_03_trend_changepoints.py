import pandas as pd
from prophet import Prophet
from prophet.plot import add_changepoints_to_plot


def automatic_detection():
    df = pd.read_csv(
        'https://raw.githubusercontent.com/facebook/prophet/main/examples/example_wp_log_peyton_manning.csv')
    print(df.head())

    m = Prophet(holidays_prior_scale=5.0)
    m.fit(df)
    future = m.make_future_dataframe(periods=365)
    future.tail()

    forecast = m.predict(future)
    forecast[['ds', 'yhat', 'yhat_lower', 'yhat_upper']].tail()

    fig = m.plot(forecast)
    a = add_changepoints_to_plot(fig.gca(), m, forecast)
    fig.show()

if __name__ == '__main__':
    """
    趋势变化点
    Trend Changepoints
    https://facebook.github.io/prophet/docs/trend_changepoints.html#automatic-changepoint-detection-in-prophet
    
    有时候，实时序列的轨迹经常发生突然变化.
    
    默认情况下，Prophet 会自动检测这些变化点，并允许趋势进行适当的调整。
    
    可以使用几个输入参数，来更好控制这个过程。
    """
    automatic_detection()


