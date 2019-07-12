package org.dimigo.translator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class translatorController {
    @FXML TextArea input;
    @FXML Label output;
    @FXML Label dialog;
    @FXML TextField filename;
    @FXML RadioButton ko;
    @FXML RadioButton en;
    String in;
    String out;


    @FXML
    public void translate(){
        dialog.setVisible(false); //"저장이 완료되었습니다" 문구를 안 보이게 설정
        in = input.getText(); //input에서 입력된 값을 가져와 in에 넣음

        if(ko.isSelected()==true) { //한국어가 선택되면
            out = NaverTranslator.translate(in); //번역된 결과를 out에 넣음
        }
        else if(en.isSelected()==true) { //영어가 선택되면
            out = NaverTranslator2.translate(in);
        }
        output.setText(out); //output에 번역된 결과를 출력
    }

    @FXML
    public void tts(){
        APIExamTTS.tts(out);
    } //listen 을 누를 시 번역된결과를 넘겨서 음성으로 들려줌

    @FXML
    public void save(){
        SaveFile.save(in,out,filename.getText()); //번역 전 문자와 번역된 결과, 파일 이름을 넘겨줌
        dialog.setVisible(true); //"저장이 완료되었습니다" 문구를 보여줌
    }

}

