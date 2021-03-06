package com.cricketanalyser.test;

import com.cricketanalyser.analyse.ConstantPaths;
import com.cricketanalyser.analyse.CricketAnalyser;
import com.cricketanalyser.exception.CricketAnalyserException;
import com.cricketanalyser.model.BattingCSVFile;
import com.cricketanalyser.model.BowlingCSVFile;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CricketAnalyserTest extends ConstantPaths {
    CricketAnalyser cricketAnalyser;

    @Before
    public void initialize() {
        cricketAnalyser = new CricketAnalyser();
    }

    //UC-1
    //TC-1.1
    @Test
    public void givenCricketBattingFilePath_whenCorrect_shouldReturnsCorrectRecords() {
        try {
            int numOfRecords = cricketAnalyser.loadCricketData("BATSMAN", IPL_RUNS_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (CricketAnalyserException e) {
            System.out.println("No Record matches");
        }
    }

    //TC-1.2
    @Test
    public void givenCricketBattingFilePath_whenInCorrect_shouldThrowException() {
        try {
            cricketAnalyser.loadCricketData("BATSMAN", WRONG_IPL_RUNS_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM, e.type);
        }
    }

    //TC-1.3
    @Test
    public void givenCricketBattingFileType_whenInCorrect_shouldThrowException() {
        try {
            cricketAnalyser.loadCricketData("BATSMAN", WRONG_IPL_RUNS_FILE_TYPE);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM, e.type);
        }
    }

    //TC-1.4
    @Test
    public void givenCricketBattingData_whenInCorrect_shouldThrowException() {
        try {
            cricketAnalyser.loadCricketData("BATSMAN", WRONG_IPL_RUNS_FILE);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CRICKET_FILE_INTERNAL_ISSUE, e.type);
            System.out.println(e.getMessage());
        }
    }

    //TC-1.5
    @Test
    public void givenCricketBattingData_whenAverage_shouldReturnTopAverage() {
        try {
            cricketAnalyser.loadCricketData("BATSMAN", IPL_RUNS_FILE_PATH);
            String sortedData = cricketAnalyser.getFieldWiseData("average");
            BattingCSVFile[] cricketCSV = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("MS Dhoni", cricketCSV[0].player);
        } catch (CricketAnalyserException e) {
        }
    }

    //UC-2
    //TC-2.1
    @Test
    public void givenCricketBattingData_whenStrikingRate_shouldReturnCorrectRecord() {
        try {
            cricketAnalyser.loadCricketData("BATSMAN", IPL_RUNS_FILE_PATH);
            String sortedData = cricketAnalyser.getFieldWiseData("strike_rate");
            BattingCSVFile[] cricketCSV = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("Ishant Sharma", cricketCSV[0].player);
        } catch (CricketAnalyserException e) {
        }
    }

    //UC-3
    //TC-3.1
    @Test
    public void givenCricketBattingData_whenSixsAndFours_shouldReturnCorrectRecord() {
        try {
            cricketAnalyser.loadCricketData("BATSMAN", IPL_RUNS_FILE_PATH);
            String sortedData = cricketAnalyser.getFieldWiseData("six_four");
            BattingCSVFile[] cricketCSV = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("Andre Russell", cricketCSV[0].player);
        } catch (CricketAnalyserException e) {
        }
    }

    //UC-4
    //TC-4.1
    @Test
    public void givenCricketBattingData_whenTopSixesFoursAndStrikingRate_shouldReturnCorrectRecord() {
        try {
            cricketAnalyser.loadCricketData("BATSMAN", IPL_RUNS_FILE_PATH);
            String sortedData = cricketAnalyser.getFieldWiseData("strike_and_six_four");
            BattingCSVFile[] cricketCSV = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("Andre Russell", cricketCSV[0].player);
        } catch (CricketAnalyserException e) {
        }
    }

    //UC-5
    //TC-5.1
    @Test
    public void givenCricketBattingData_whenTopAverageWithStrikeRate_shouldReturnCorrectRecord() {
        try {
            cricketAnalyser.loadCricketData("BATSMAN", IPL_RUNS_FILE_PATH);
            String sortedData = cricketAnalyser.getFieldWiseData("avg_and_strike_rate");
            BattingCSVFile[] cricketCSV = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("Ishant Sharma", cricketCSV[0].player);
        } catch (CricketAnalyserException e) {
        }
    }

    //UC-6
    //TC-6.1
    @Test
    public void givenCricketBattingData_whenMaxRunsWithAverage_shouldReturnCorrectRecord() {
        try {
            cricketAnalyser.loadCricketData("BATSMAN", IPL_RUNS_FILE_PATH);
            String sortedData = cricketAnalyser.getFieldWiseData("runs_and_avg");
            BattingCSVFile[] cricketCSV = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("David Warner ", cricketCSV[0].player);
        } catch (CricketAnalyserException e) {
        }
    }

    //UC-7
    //TC-7.1
    @Test
    public void givenCricketBowlingData_whenAverage_shouldReturnTopAverage() {
        try {
            cricketAnalyser.loadCricketData("BOWLER", IPL_BALLS_FILE_PATH);
            String sortedData = cricketAnalyser.getFieldWiseData("average");
            BowlingCSVFile[] cricketCSV = new Gson().fromJson(sortedData, BowlingCSVFile[].class);
            Assert.assertEquals("Anukul Roy", cricketCSV[cricketCSV.length-1].player);
        } catch (CricketAnalyserException e) {
        }
    }

    //UC-8
    //TC-8.1
    @Test
    public void givenCricketBowlingData_whenStrikingRate_shouldReturnCorrectRecord() {
        try {
            cricketAnalyser.loadCricketData( "BOWLER", IPL_BALLS_FILE_PATH );
            String sortedData = cricketAnalyser.getFieldWiseData( "strike_rate" );
            BowlingCSVFile[] cricketCSV = new Gson().fromJson( sortedData, BowlingCSVFile[].class );
            Assert.assertEquals( "Alzarri Joseph", cricketCSV[cricketCSV.length-1].player );
        } catch (CricketAnalyserException  e) {
        }
    }

    //UC-9
    //TC-9.1
    @Test
    public void givenCricketBowlingData_whenEconomy_shouldReturnCorrectRecord() {
        try {
            cricketAnalyser.loadCricketData( "BOWLER", IPL_BALLS_FILE_PATH );
            String sortedData = cricketAnalyser.getFieldWiseData( "economy" );
            BowlingCSVFile[] cricketCSV = new Gson().fromJson( sortedData, BowlingCSVFile[].class );
            Assert.assertEquals( "Shivam Dube", cricketCSV[cricketCSV.length-1].player );
        } catch (CricketAnalyserException  e) {
        }
    }

    //UC-10
    //TC-10.1
    @Test
    public void givenCricketBowlingData_whenStrikeRateAndWickets_shouldReturnCorrectRecord() {
        try {
            cricketAnalyser.loadCricketData("BOWLER",IPL_BALLS_FILE_PATH);
            String sortedData = cricketAnalyser.getFieldWiseData("strike_and_wickets");
            BowlingCSVFile[] cricketCSV = new Gson().fromJson( sortedData, BowlingCSVFile[].class );
            Assert.assertEquals( "Kagiso Rabada",cricketCSV[cricketCSV.length-1].player );
        } catch (CricketAnalyserException  e) {
        }
    }

    //UC-11
    //TC-11.1
    @Test
    public void givenCricketBowlingData_whenTopAverageWithStrikeRate_shouldReturnCorrectRecord() {
        try {
            cricketAnalyser.loadCricketData("BOWLER",IPL_BALLS_FILE_PATH);
            String sortedData = cricketAnalyser.getFieldWiseData( "avg_and_strike_rate" );
            BowlingCSVFile[] cricketCSV = new Gson().fromJson( sortedData, BowlingCSVFile[].class );
            Assert.assertEquals( "Alzarri Joseph", cricketCSV[cricketCSV.length-1].player );
            //Assert.assertEquals( "Kagiso Rabada", cricketCSV[cricketCSV.length-1].player )
        } catch (CricketAnalyserException  e) {
        }
    }

    //UC-12
    //TC-12.1
    @Test
    public void givenCricketBowlingData_whenTopAverageWithWickets_shouldReturnCorrectRecord() {
        try {
            cricketAnalyser.loadCricketData("BOWLER",IPL_BALLS_FILE_PATH);
            String sortedData = cricketAnalyser.getFieldWiseData( "wicket_average" );
            BowlingCSVFile[] cricketCSV = new Gson().fromJson( sortedData, BowlingCSVFile[].class );
            Assert.assertEquals( "Lasith Malinga", cricketCSV[0].player );
        } catch (CricketAnalyserException  e) {
        }
    }

    //TC-13.1
    @Test
    public void givenIPLData_whenSortedTopBattingAndBowlingAverage_shouldReturnCorrectRecord() {
        try {
            String sortedData = cricketAnalyser.mergeBatsMenBowlerData( ConstantPaths.IPL_RUNS_FILE_PATH,
                    ConstantPaths.IPL_BALLS_FILE_PATH, "batsman_bowler_average");
            BowlingCSVFile[] cricketCSV = new Gson().fromJson( sortedData, BowlingCSVFile[].class );
            Assert.assertEquals(  "Andre Russell", cricketCSV[0].player );
        } catch (CricketAnalyserException  e) {
            e.printStackTrace();
        }
    }

    //UC-14
    //TC-14.1
    @Test
    public void givenIPLData_whenSortedTopBattingAndBowlingAverageAndRuns_shouldReturnCorrectRecord() {
        try {
            String sortedData = cricketAnalyser.mergeBatsMenBowlerData( ConstantPaths.IPL_RUNS_FILE_PATH,
                    ConstantPaths.IPL_BALLS_FILE_PATH, "all_rounder");
            BowlingCSVFile[] cricketCSV = new Gson().fromJson( sortedData, BowlingCSVFile[].class );
            Assert.assertEquals( "Mohammad Nabi", cricketCSV[0].player );
        } catch (CricketAnalyserException  e) {
            e.printStackTrace();
        }
    }
}