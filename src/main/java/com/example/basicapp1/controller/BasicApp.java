package com.example.basicapp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/** 
 * コントローラークラス 
 * @Controllerはコントローラークラスであることを示すアノテーション
 * @RequestMappingはURLを指定するアノテーション
*/
@Controller
@RequestMapping("/basicapp")
public class BasicApp {

    /** 
     * ホーム画面 
     * @GetMappingはGETリクエストを受け取るアノテーション
    */
    @GetMapping("/")
    public String index() {
        return "index"; // index.htmlを返す
    }

    /** 
     * CSVファイルのアップロード 
     *  @PostMappingはPOSTリクエストを受け取るアノテーション
     *  @Requestparamはリクエストパラメータを受け取るアノテーション
     *  @Modelはビューに渡すデータを保持するクラス
    */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) {
        List<String[]> csvData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                csvData.add(line.split(","));
            }

        } catch (IOException e){
            // csvファイルの読み込みエラー処理
            // エラーメッセージをモデルに追加
            model.addAttribute("errorMessage", "CSVファイルの読み込みに失敗しました。");


        } catch (Exception e) {
            // その他のエラー処理
            // エラーメッセージをモデルに追加
            model.addAttribute("errorMessage", "CSVファイルのアップロードに失敗しました。");

        }
        model.addAttribute("csvData", csvData); // csvDataをモデルに追加

        return "upload"; // upload.htmlを返す
    }
}
