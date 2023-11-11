/*
 * @Description: 
 * @Author: Rabbiter
 * @Date: 2023-03-05 20:17:11
 */
module.exports = {
  publicPath: "./",
  //打包输出文件夹
  outputDir:"dms",
  chainWebpack: config => {
    //关闭语法检查
    config.module.rules.delete('eslint');
    config.plugin('html').tap((args) => {
      args[0].title = 'myproject';
      return args;
    });
  },
  //错误输出为编译警告
  lintOnSave: false,
  devServer: {
    open: true,
    port: 9142,
    https: false
  }
}
