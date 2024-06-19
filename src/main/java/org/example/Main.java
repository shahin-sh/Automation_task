package org.example;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Main {
    public static void main(String[] args) {
        WebDriver driver=new EdgeDriver();
        driver.get("google.com");
    }
}