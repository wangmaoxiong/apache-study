package com.wmx.apachestudy.pinying;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.util.Assert;

/**
 * PinYin4j 中文转拼音工具类
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/6/3 20:29
 */
public class PinYin4jUtil {

    /**
     * 汉字转汉语拼音。这是底层方法，然后提供重载方法，简化参数传递
     * {@// TODO: 2020/6/4 因为汉语的博大精深，所以对于多音字的处理本方法暂时无法提供有效支持 }
     * <p>
     * {@link HanyuPinyinOutputFormat} 汉语拼音输出格式设置
     * {@link HanyuPinyinCaseType} 设置拼音大小写：UPPERCASE（大写）、LOWERCASE（小写）
     * {@link HanyuPinyinToneType} 设置拼音音调：WITHOUT_TONE（无音标）、WITH_TONE_MARK（拼音上面带音标）、WITH_TONE_NUMBER（用1-4表示音调）
     * {@link HanyuPinyinVCharType} 设置特殊音标ü：WITH_V（用 v 表示 ü）、WITH_U_AND_COLON（用 "u:" 表示 ü）、WITH_U_UNICODE（直接用 ü）
     * 注意：toneType 为 WITH_TONE_MARK 时，charType 必须为 WITH_U_UNICODE，否则异常。
     *
     * @param text     ：待转换的源字符串，不能为 null
     * @param caseType ：拼音大小写，1 表示大写，2 表示小写，3 表示首字母大写
     * @param toneType ：拼音音调，1 无音调，2 拼音上方加音调，3 用数字表示音调
     * @param charType ：特殊音标ü显示方式，1 用 v 显示，2 用 ü 表示，3 用 u: 表示
     * @param spacer   ：拼音与拼音之间的分隔符
     * @return
     */
    public static String textToHanYuPinyin(String text, int caseType, int toneType, int charType, String spacer) {
        StringBuffer result = new StringBuffer();
        try {
            Assert.notNull(text, "待转换文本不能为空！");
            HanyuPinyinCaseType pinyinCaseType = caseType == 1 ? HanyuPinyinCaseType.UPPERCASE : HanyuPinyinCaseType.LOWERCASE;
            HanyuPinyinToneType pinyinToneType = toneType == 1 ? HanyuPinyinToneType.WITHOUT_TONE : toneType == 2 ? HanyuPinyinToneType.WITH_TONE_MARK : HanyuPinyinToneType.WITH_TONE_NUMBER;
            HanyuPinyinVCharType pinyinVCharType = charType == 1 ? HanyuPinyinVCharType.WITH_V : charType == 2 ? HanyuPinyinVCharType.WITH_U_UNICODE : HanyuPinyinVCharType.WITH_U_AND_COLON;
            //toneType 为 WITH_TONE_MARK 时，charType 必须为 WITH_U_UNICODE，否则异常。
            pinyinVCharType = toneType == 2 ? HanyuPinyinVCharType.WITH_U_UNICODE : pinyinVCharType;
            char[] textChar = text.trim().toCharArray();
            HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
            hanyuPinyinOutputFormat.setCaseType(pinyinCaseType);
            hanyuPinyinOutputFormat.setToneType(pinyinToneType);
            hanyuPinyinOutputFormat.setVCharType(pinyinVCharType);
            for (int i = 0; i < textChar.length; i++) {
                // 只为汉字进行拼音转换。
                if (Character.toString(textChar[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    /**toHanyuPinyinStringArray
                     * 获取单个汉字的所有汉语拼音，即对于多音字，如"间"，则返回两个汉语拼音串：jian1、jian4
                     * 这里暂时只取第一个，实际中这样是存在误差的，多音字很容易取错拼音.
                     */
                    String[] pinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(textChar[i], hanyuPinyinOutputFormat);
                    String fullPinYin = pinyinStringArray[0];
                    //caseType 表示将首字母大写
                    fullPinYin = caseType == 3 ? Character.toString(fullPinYin.charAt(0)).toUpperCase() + fullPinYin.substring(1) : fullPinYin;
                    result.append(fullPinYin).append(spacer);
                } else {
                    result.append(textChar[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 重载方法，简化参数传递，使用默认参数
     *
     * @param text
     * @return
     */

    public static String textToHanYuPinyin(String text) {
        return textToHanYuPinyin(text, 3, 1, 1, "");
    }

    public static String textToHanYuPinyin(String text, int caseType) {
        return textToHanYuPinyin(text, caseType, 1, 1, "");
    }

    public static String textToHanYuPinyin(String text, int caseType, int toneType) {
        return textToHanYuPinyin(text, caseType, toneType, 1, "");
    }

    public static String textToHanYuPinyin(String text, int caseType, String spacer) {
        return textToHanYuPinyin(text, caseType, 1, 1, spacer);
    }

    public static String textToHanYuPinyin(String text, int caseType, int toneType, int charType) {
        return textToHanYuPinyin(text, caseType, toneType, charType, "");
    }

    public static void main(String[] args) {
        String text = "修长城的民族，2022 年天朝万岁！";
        System.out.println("源字符串：" + text);
        System.out.println("1、" + textToHanYuPinyin(text, 3, 2, 3, " "));
        System.out.println("2、" + textToHanYuPinyin(text, 3, 2, 3));
        System.out.println("3、" + textToHanYuPinyin(text, 3, 2));
        System.out.println("4、" + textToHanYuPinyin(text, 3, "|"));
        System.out.println("5、" + textToHanYuPinyin(text, 2));
        System.out.println("6、" + textToHanYuPinyin(text));

//        MultiPinyinConfig.multiPinyinPath="/Users/yiboliu/my_multi_pinyin.txt"
    }
}
