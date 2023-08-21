# 导入线性模型模块
from sklearn import linear_model
from sklearn.metrics import mean_squared_error, r2_score

from MyDatasets import X_train, y_train, X_test, y_test, X, y

# 创建LASSO回归模型实例
sk_LASSO = linear_model.Lasso(alpha=0.1)
# 对训练集进行拟合
sk_LASSO.fit(X_train, y_train)

y_pred = sk_LASSO.predict(X_test)

# 输出模型均方误差
print("Mean squared error: %.2f" % mean_squared_error(y_test, y_pred))
# 计算R2系数
print('R Square score: %.2f' % r2_score(y_test, y_pred))

# 打印模型相关系数
print("sklearn LASSO intercept :", sk_LASSO.intercept_)
print("\nsklearn LASSO coefficients :\n", sk_LASSO.coef_)
print("\nsklearn LASSO number of iterations :", sk_LASSO.n_iter_)