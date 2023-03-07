package Test.Level1;

/**
 * 문자열에서 없는 알파벳 추출하기.
 */
public class test1 {
    
    public static void main(String[] args) {

        //---------------------------------------------------------------------------------------------------------------------------------
        String sentence = "His comments came after Pyongyang announced it had a plan to fire four missiles near the US territory of Guam.";
        /*String sentence = "Jackdaws love my big sphinx of quartz";*/
        //공백제거
        String sentenceReplaceAll = sentence.replaceAll(" ", "");
        //특수문자제거
        String removeSentence = sentenceReplaceAll.replaceAll("[.]", "");
        //대문자 =>소문자변경
        String removeSentence2 = removeSentence.toLowerCase();
        //알파벳
        String alpahbetword = "abcdefghijklmnopqrstuvwxyz";
        char c = 'a';
        StringBuilder stringBuilder1 = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            stringBuilder1.append(c);
            c+=1;
        }
        //문자열 추가 객체 생성
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i <26 ; i++) {
            int alphabet = removeSentence2.indexOf(stringBuilder1.charAt(i));
            if(alphabet <0){
                char word = stringBuilder1.charAt(i);
                stringBuilder.append(word);
            }
        }
        if(stringBuilder.length() == 0){
            stringBuilder.append("perfect");
        }
        System.out.println(stringBuilder);
        //---------------------------------------------------------------------------------------------------------------------------------
    }
}
