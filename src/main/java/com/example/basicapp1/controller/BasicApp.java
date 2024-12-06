package com.example.basicapp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        // 拡張子がcsvでない場合はエラーメッセージを表示
        Optional<String> optionalFilename = Optional.ofNullable(file.getOriginalFilename());
        if ( optionalFilename.isPresent() || !optionalFilename.get().endsWith(".csv") ) {
            model.addAttribute("errorMessage", "CSVファイルを選択してください。");
            return "index";
        }

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

    /**
     * CSVファイルのダウンロード
     * @return CSVファイルのダウンロード
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> download() {
        // サンプルデータの作成
        List<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> row1 = new ArrayList<>();
        row1.add("header1");
        row1.add("header2");
        row1.add("header3");
        data.add(row1);
        ArrayList<String> row2 = new ArrayList<>();
        row2.add("value1");
        row2.add("value2");
        row2.add("value3");
        data.add(row2);

        // データをCSV形式に変換
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
            for (ArrayList<String> row : data) {
                writer.write(String.join(",", row));
                writer.write("\n");
            }
        } catch (IOException e) {
            // エラー処理
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        byte[] csvBytes = outputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.csv");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
    }
}
