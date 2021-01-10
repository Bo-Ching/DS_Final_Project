import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

public class GoogleQuery

{

 public String searchKeyword;

 public String url;

 public String content;
 public ArrayList<String> search = new ArrayList<String>();
 public ArrayList<WebPage> weblist = new ArrayList<WebPage>();

 public GoogleQuery(String searchKeyword)

 {

  this.searchKeyword = searchKeyword;

  this.url = "http://www.google.com/search?q=" + searchKeyword + "&oe=utf8&num=20";

 }

 private String fetchContent() throws IOException

 {
  String retVal = "";

  URL u = new URL(url);

  URLConnection conn = u.openConnection();

  conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

  InputStream in = conn.getInputStream();

  InputStreamReader inReader = new InputStreamReader(in, "utf-8");

  BufferedReader bufReader = new BufferedReader(inReader);
  String line = null;

  while ((line = bufReader.readLine()) != null) {
   retVal += line;

  }
  return retVal;
 }

 public HashMap<String, String> query() throws IOException

 {

  if (content == null)

  {

   content = fetchContent();

  }

  HashMap<String, String> retVal = new HashMap<String, String>();

  Document doc = Jsoup.parse(content);
  // System.out.println(doc.text());
  Elements lis = doc.select("div");
  // System.out.println(lis);
  lis = lis.select(".kCrYT");
  // System.out.println(lis.size());

  for (Element li : lis) {
   try

   {
    String citeUrl = li.select("a").get(0).attr("href");
    String title = li.select("a").get(0).select(".vvjwJb").text();
    // System.out.println(citeUrl.substring(7, citeUrl.length()));
    retVal.put(title, citeUrl.substring(7, citeUrl.length()));
    search.add(citeUrl.substring(7, citeUrl.length()));

   } catch (IndexOutOfBoundsException e) {

//    e.printStackTrace();

   }

  }

  return retVal;

 }

 public void SetScore() throws IOException {
  ArrayList<Keyword> k = new ArrayList<Keyword>();
  k.add(new Keyword("北海道", 2));
  k.add(new Keyword("沖繩", 2));
  k.add(new Keyword("名古屋", 2));
  k.add(new Keyword("東京", 2));
  k.add(new Keyword("大阪", 2));
  double score = 0;
  for (String key : query().keySet()) {
   try {
    WebPage w = new WebPage(query().get(key), key, score);
    score = w.setScore(k);
    weblist.add(new WebPage("http://www.google.com.tw/url?q="+query().get(key), key, score));
   } catch (IOException e) {
    // TODO Auto-generated catch block
    // e.printStackTrace();
    weblist.add(new WebPage("http://www.google.com.tw/url?q="+query().get(key), key, 0));
   }
  }
 }

 public void quickSort(int leftbound, int rightbound) {
  if(rightbound<=leftbound)
   return;
  int pivotindex=(leftbound+rightbound)/2;
  double pivot=weblist.get(pivotindex).score;
  swap(pivotindex,rightbound);
  int swapindex=leftbound;
  for(int i=leftbound;i<rightbound;i++) {
   if(weblist.get(i).score<=pivot) {
    swap(i,swapindex);
    ++swapindex;
   }
  }
  swap(swapindex,rightbound);
  quickSort(leftbound,swapindex-1);
  quickSort(swapindex + 1, rightbound);
 }

 private void swap(int aIndex, int bIndex) {

  WebPage temp = weblist.get(aIndex);
  weblist.set(aIndex, weblist.get(bIndex));
  weblist.set(bIndex, temp);
 }

 public ArrayList<WebPage> getWebPage() {
  return weblist;
 }

 public void sort() {
  quickSort(0, weblist.size() - 1);
 }
}
