package org.dimigo.translator;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFile {
    public static void save(String in,String out,String filename) {
        String message = in; //번역 전 문자
        String message2 = out; //번역된 결과

        File file = new File("C:\\Users\\songhui\\Desktop\\"+filename+".txt"); //바탕화면에 새파일 생성
        FileWriter writer = null;

        try {
            writer = new FileWriter(file, false);// 기존 내용을 없애고 새로 작성
            writer.write(message); //번역 전 문자를 쓰고
            writer.write("  :  "); // :를 쓰고
            writer.write(message2); //번역 된 결과를 작성
            writer.flush();

            System.out.println("저장 완료 !");

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null) writer.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}



