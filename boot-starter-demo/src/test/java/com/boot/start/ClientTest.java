package com.boot.start;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/10/10 下午5:00
 * @see jdk 1.7
 **/
public class ClientTest {

    public static final String http = "http://localhost:8080/long-polling";

//    private final static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
//        @Override
//        public Thread newThread(Runnable r) {
//            return new Thread(r, "MQClientFactoryScheduledThread");
//        }
//    });

    public static void main(String[] args) {

//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

            int i = 0;

//            @Override
//            public void run() {

                while (true) {
                    final long startTimeMillis = System.currentTimeMillis();
                    System.out.println("第" + i++ + "次轮询");
                    HttpURLConnection connection = null;
                    try {

                        URL url = new URL(http);
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setReadTimeout(20000); //这就是设置读取等待时间设置为20s，超时，关闭连接重新拉取
                        connection.setConnectTimeout(3000); //3s
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("Accept-Charset", "utf-8");
                        connection.setRequestProperty("Content-Type", "application/json");
                        connection.setRequestProperty("Charset", "UTF-8");

                        final int responseCode = connection.getResponseCode();
                        if (200 == responseCode) {
                            final InputStream inputStream = connection.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                            try {
                                StringBuilder builder = new StringBuilder();
                                String line = null;
                                while ((line = bufferedReader.readLine()) != null) {
                                    builder.append(line);
                                }

                                System.out.println("result = " + builder.toString());
                            } finally {
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(" request failed ");
                    } finally {
                        final long endTimeMillis = System.currentTimeMillis() - startTimeMillis;
                        System.out.println("connection close" + "     " + "elapse " + endTimeMillis + "s");
                        connection.disconnect();
                    }
                }
//            }

//        }, 1, 1, TimeUnit.MILLISECONDS);
    }
}
