import pandas as pd
from prophet import Prophet

#
# 解决 libtbb.dylib 找不到的问题
# install_name_tool -add_rpath @executable_path/cmdstan-2.26.1/stan/lib/stan_math/lib/tbb   ./venv/lib/python3.9/site-packages/prophet/stan_model/prophet_model.bin
# 参考：https://github.com/facebook/prophet/issues/2250
#
if __name__ == '__main__':
    df = pd.read_csv('https://raw.githubusercontent.com/facebook/prophet/main/examples/example_wp_log_peyton_manning.csv')
    df.head()

    m = Prophet(holidays_prior_scale=5.0)
    m.fit(df)

    future = m.make_future_dataframe(periods=365)
    future.tail()

    forecast = m.predict(future)
    forecast[['ds', 'yhat', 'yhat_lower', 'yhat_upper']].tail()

    fig1 = m.plot(forecast).show()

    fig2 = m.plot_components(forecast).show()
