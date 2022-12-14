package ru.liga;

import ru.liga.dto.DateAndCourse;
import ru.liga.graph.LineChartForCurrencyExchangeRateForecasting;
import ru.liga.predication.IPredication;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static ru.liga.dto.UserDto.*;
import static ru.liga.utils.CSVReader.getCSVRows;

public class ExchangeRate {


    public static void main(String[] args) throws IOException {
        initConsole();
    }

    public static void invoke(Scanner scan) throws IOException {
        System.out.print("Прогнозирование курса валют. Введите запрос, по образцу:\"USD tomorrow или USD week.\" " +
                "\nВведите Ваш запрос - ");
        String request = scan.nextLine();
        String outputType = getOutputType(request);
        String predicatorType = getPredicatorType(request);
        String algorithmType = getAlgorithmType(request);
        IPredication predicator = IPredication.select(predicatorType);
        if (outputType.equals("") || outputType.equals("output list")) {
            String currencyType = getCurrencyType(request);
            List<DateAndCourse> csvRows = getCSVRows(currencyType);
            System.out.println(request + ":");
            for (DateAndCourse result : predicator.rate(csvRows, algorithmType)) {
                System.out.println(result.toString(currencyType));
            }
        } else {
            List<String> numberOfCurr = numberOfCurrencies(request);
            LineChartForCurrencyExchangeRateForecasting graph = new LineChartForCurrencyExchangeRateForecasting();
            graph.initUI(numberOfCurr, predicator, algorithmType);



        }
        initConsole();
    }

    public static void initConsole() throws IOException {
        Scanner scan = new Scanner(System.in);
        try {
            invoke(scan);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            initConsole();
        }
    }

}