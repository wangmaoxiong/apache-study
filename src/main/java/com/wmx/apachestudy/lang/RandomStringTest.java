package com.wmx.apachestudy.lang;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/17 16:08
 */
public class RandomStringTest {
    public static void main(String[] args) {
        new RandomStringTest().test1();
    }

    public void test1() {
        /**
         * random(final int count): 创建长度为指定个数（count）的随机字符串，将从所有字符集中选择字符，不含字母和数字，如 "篊𣶇ࢦ𣿎椗彩𩬉𦿣𦃹뢂垅"
         * random(final int count, final boolean letters, final boolean numbers)：生成指定个数（count）的随机字符串，字符将从参数指示的字母或数字字符集中选择.
         *      1、letters: 字母字符集、numbers：数字字符集
         *      2、letters 为 true、numbers 为 true：则随机字符串由字母和数字组成
         *      3、letters 为 true、numbers 为 false：则随机字符串由字母组成
         *      4、letters 为 false、numbers 为 true：则随机字符串由数字组成，如 7253440222803746
         *      5、letters 为 false、numbers 为 false：则等同于 random(final int count)
         * random(final int count, final char... chars)：创建长度为指定个数的随机字符串，从指定的字符集(chars)中选择字符. chars 的个数可以小于 count
         * String random(final int count, final String chars): 从指定的字符集中选择字符生成指定个数的随机字符串
         */
        char[] chars = new char[]{'1', '2', '3', '4', '5', 'a', 'b', 'c', 'd', 'e', '中', '国', '秦', '始', '皇'};
        String random = RandomStringUtils.random(16);
        String random1 = RandomStringUtils.random(16, false, true);
        String random2 = RandomStringUtils.random(16, chars);
        String random3 = RandomStringUtils.random(16, "元济不得命乃悉兵四出焚舞阳及叶掠襄城阳翟");

        //如：random=𪧺𩯢譙𫘾𨣴䝏𡧮𠸴㹵膉
        System.out.println("random=" + random);
        //如：random1=6341306804529422
        System.out.println("random1=" + random1);
        //如：random2=553国dc4bd秦国中国秦中e
        System.out.println("random2=" + random2);
        //random3=不不襄阳元四济出阳元不出城不阳济
        System.out.println("random3=" + random3);
    }

    public void test2() {
        /**
         * randomAlphabetic(final int count): 创建指定长度的随机字符串，字符将从 (a-z, A-Z) 中选择，等同于 random(count, true, false)
         * randomAlphabetic(final int minLengthInclusive, final int maxLengthExclusive)：创建长度介于 [minLengthInclusive,maxLengthExclusive) 之间的随机字符串
         * randomAlphanumeric(final int count)：创建长度为指定字符数的随机字符串，从拉丁字母字符集（a-z, A-Z）和数字0-9中选择，等同于 random(count, true, true)
         * randomAlphanumeric(final int minLengthInclusive, final int maxLengthExclusive)：创建长度介于 [minLengthInclusive,maxLengthExclusive) 之间的随机字符串
         * randomAscii(final int count)：随机字符将从 ASCII 码值介于 [32,126] 之间的字符集中选择，等价于：random(count, 32, 127, false, false)
         * randomAscii(final int minLengthInclusive, final int maxLengthExclusive)：创建的随机字符串个数介于 [minLengthInclusive,maxLengthExclusive)
         * randomGraph(final int count): 随机字符从所有可见的 ASCII 字符中选择，即除空格和控制字符外的任何内容，等价于：random(count, 33, 126, false, false)
         * randomGraph(final int minLengthInclusive, final int maxLengthExclusive)
         * randomNumeric(final int count): 创建长度为指定字符数的随机字符串，随机字符将从数字字符集中选择。
         * randomNumeric(final int minLengthInclusive, final int maxLengthExclusive)
         * randomPrint(final int count): 随机字符从所有可见的 ASCII 码字符和空格(即除控制字符外的任何内容）中选择。等价于 random(count, 32, 126, false, false)
         */
        String randomAlphabetic = RandomStringUtils.randomAlphabetic(16);
        String randomAlphabetic1 = RandomStringUtils.randomAlphabetic(16, 18);
        String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(16);
        String randomAscii = RandomStringUtils.randomAscii(16);
        String randomGraph = RandomStringUtils.randomGraph(16);
        String randomNumeric = RandomStringUtils.randomNumeric(16);
        String randomPrint = RandomStringUtils.randomPrint(16);

        //randomAlphabetic=kyjTzlDGibsitaUV
        System.out.println("randomAlphabetic=" + randomAlphabetic);
        //randomAlphabetic1=yLfoKuRDnPrToYuoW
        System.out.println("randomAlphabetic1=" + randomAlphabetic1);
        //randomAlphanumeric=lpIXjQ4j5GP5BsHR
        System.out.println("randomAlphanumeric=" + randomAlphanumeric);
        //randomAscii=}~g2\pT/eIkXoa?-
        System.out.println("randomAscii=" + randomAscii);
        //randomGraph=oFsGkQm#oTS7]${2
        System.out.println("randomGraph=" + randomGraph);
        //randomNumeric=7663970486824099
        System.out.println("randomNumeric=" + randomNumeric);
        //randomPrint=dm^}p7ii>n"k%4-+
        System.out.println("randomPrint=" + randomPrint);
    }

}
