package com.boot.start;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/10/10 下午4:52
 * @see jdk 1.7
 **/
@RestController
public class LongPollingController {

    AtomicLong atomicLong = new AtomicLong();

    Random random = new Random();

    @RequestMapping("/long-polling")
    public Long LongPolling() {
        System.out.println("第" + (atomicLong.incrementAndGet()) + "次 longpolling");
        try {
            while (true) {
                Long aLong = fetchData();
                if (aLong == null) {//如果拉取数据为空，休眠
                    final int sleepSecends = random.nextInt(20);//在50里面随机产生一个数
                    System.out.println("wait " + sleepSecends + " second");
                    TimeUnit.SECONDS.sleep(sleepSecends);//休眠多少秒
                } else {
                    System.out.println("返回的数据为 = "+aLong);
                    return aLong;//返回数据
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @desc 通过模拟下有数据和没有数据的情况
     * @author lgfcxx
     * @creaetime 2018/10/10 下午5:45
     * @param
     * @return
     * @throws
     **/
    public Long fetchData() {
        final int anInt = random.nextInt(100);
        System.out.println("random=" + anInt + " 余数 = " + anInt % 2);
        if (anInt % 2 == 0) {
            return atomicLong.getAndIncrement();
        } else {
            return null;
        }
    }
}
