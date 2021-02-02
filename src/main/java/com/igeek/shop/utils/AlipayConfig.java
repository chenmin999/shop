package com.igeek.shop.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000120605370";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuHa3VLUN/1am/g1Ac52TgQyQaRSRwnuePXBpekHoT6p3FSSGdLUbKRxpT4MKrQgdBmnOpJZXzeWrlMlsxJ/4iRjGVqsfKMehrFihAieJKDLBaNYJmY7oHr3U0Vyd6HtR37tusM2GlHLkT+/VxdjqFno/UzcTaU4/SLbeVaQF7xCHZJFKWa37VJIuiQvPplJQigrEuG/XBBrYyEP4wDlytqkvwTtZUFUEJye12QcPtPFw6xTFONTftS6+hewCoeWrDE0Q6/T0Q0FV/tKw4qxbg/t1kLE3kFomsGO2SkYGGz1f2IjiPrOAzqdP+SSxKolZoB6TXmQAavW9fPTlqnlVbAgMBAAECggEBAIYjw1qqxofj2iT7S2sZMfQ8hcHfZtWE9qzRrlcQQ5y4cdgeIVFwIkuQP85DKHa8Jc766QR1OnMbJalyBCN4xictoOHHcqhpjr+BkH04PSgDYpH9C3tKdXWaTVeHlZhVzCah1Id0BlQoLsYgpNVG2os6v2CZLoFtQy4PnpHzFft8snhSpYWx0iz/m4tzPWJRlgZNhJFJOtKahE5OClK2yBtJSRe0VkmFLaQGn+/TrkUP8fKYQwNTlQNyyF3kK4DLrNSwXfL+0WQSzrM4127i/qeVdsnocy7SHiHQcuTKKcIqHCxfqFJFwmwOFuONm4BeR4Ld5CfFUnKPPajdK/tE7rECgYEA/HqZJBAVIWlthMzIVyIcm81rtR2Ig3ZA3bXleh2Mec0ZpK7KIfwYFT4TxwtlFfkDrju0aFE2Wr2hduocKjcIkIJCEqbiczgmE9npbBYP7KALf9QtAE9s0JOm9AtF0Hd9VDoTJ3CnSewbn8+vxBr82TdAB+qz7avs7PDmOrBfH+UCgYEAsItPEaR9H3966QuoBJSwivdh8yg+xY69FvUN+RyNFmQnIMvhQgoSNPy4RQeY8I3HnePrBf8PrOlfBQawC/2ozN2gK7CoIGHD6IWEx6+uJo9BhHHfk+ETfCFi5FvWdpj1rrkrbTqSz9vhOGb/tdpvl5x3TeGFCgl7GAuTesPvzD8CgYEAwmkkwsAfhTrJOhj1qyJ5JP9okG3NUG0eRZpquT1HjgKAXG669rBHWxQR5pK/o8u7Kc6PfEQMtpKOYiKizV7TGXMzrWkTteTmbSK+upvCZ1JViHi4riR6haEsTX7go0VfHBwb46K7LhDAtK4Xicm+KQSCKZZwNfLM3SYiIc7tcIECgYBWZrd5wPDjm3ZB28yNAWAtgsjTQh0GfscYFzKRemGqVbW4nU9PDLLAb/aXAhLdl+Xb5hG9y4iWbVhtk0pRdAFK0iRzfW+q+W3xvHaxa89lW7MK6Ngs/XiVxZkqfa/GNzH2IvWX7ZSwWcIt4mq/S3pVzuOsn/SGZwFxUSkKah5P7QKBgG2xmyO9TKgSsORP3FN4mEtZ5gpI/z/YWoJBzZbXqyCuoGjW12YP62hd4RV/jBVboEClqnWEHO5NMxxzukQp7gB+kPv6Z+rnq4pcgTnuTxJBul3EC05p4j5W7Bzmt3wE89v9qqIOBxS5AFoprXJ39UpXZ5S5nLjrV47bQVwtc5z2";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzfTZAMG9D+IxAnLVfmZ+QseiSuVwJQNNwR1DVmbJoOzEWQ+MTi1uRDQaFCTWY4V4Ypti9zAsRAIvGbg8GjH8omPKN1+AWZo7XMvJqSUoojFLtXu8lSF+gdyBKJQ7HcArI6YrEP7aWZb9Sx0vz0mX0VMADCeHrD1fTEMXyhV9oBDf5sbO6nl+iWpNrIcY71yP2kNrsaLJRKQMaWhIkx74JtD35Ge3kewQkkf5WTzZ8cc8M9TC3eDxZp7obQLC6WklrXEoa6jJmhUg37N39pHN8xdCr8G6ybeq041fNFUNajFzkyxHUfsG2xuAR1jaGTryhe+T9QaeyFIT2ItljHrYFwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = " http://freemark.free.idcfengye.com/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = " http://freemark.free.idcfengye.com/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

