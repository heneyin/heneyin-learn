# 导入线性回归模块
from sklearn import linear_model
from sklearn.metrics import mean_squared_error, r2_score
# 数据集
from MyDatasets import X_train, y_train, X_test, y_test, X, y

# 定义模型实例
regr = linear_model.LinearRegression()
# 模型拟合训练数据
regr.fit(X_train, y_train)
# 模型预测值
y_pred = regr.predict(X_test)
# 输出模型均方误差
print("Mean squared error: %.2f" % mean_squared_error(y_test, y_pred))
# 计算R2系数
print('R Square score: %.2f' % r2_score(y_test, y_pred))

# x_length = np.array([i for i in range(len(X_test))])

import matplotlib.pyplot as plt
import numpy as np

# fig, ax = plt.subplots()
# ax.scatter(x_length, y_test, marker="o")
# ax.scatter(x_length, y_pred)
# # ax.plot([y_test.min(), y_test.max()], [X_test.min(), X_test.max()], 'k--', lw=4)
# ax.set_xlabel('Measured')
# ax.set_ylabel('Predicted')
# plt.show()
