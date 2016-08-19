Weather Forecaster
==================

Weather Forecaster is a Java utility to forecast the weather data for the upcoming days. Historical weather data is considered to forecast the weather for the future dates. In this version following approach is taken to forecast the weather.

Weather Stations are configured using CSV files. Historical weather of 2015 is taken as base year which is also stored in CSV form. Weather history contains mainly the minimum, maximum and average temperatures of every day.  A csv based db query engine csvjdbc is used to query the csv files for a particular date.  Each day is divided into different timeslots and the randomized temperature for that particular timeslot is calculated with lower bound and upper bound as min,max or avg temperature. This way hourly temperature is taken. Other parameters like pressure, relative humidity and events are calculated with the temperature, elevation and other known parameters. This is finally encapsulated into the environment model and emitted in the below format.
Temperatures within timeslots can be sorted to get a natural/gradient effect. This part is not coded yet.


Eg: SYD|-33.865143|151.2099|2016-07-14T11:49:26.945+05:30|SUNNY|15.643266077567532|1012.8168774204457 |6.

This approach is just to simulate a toy model of future environment from the base year’s weather data. So many improvements is possible within this approach even though the approach is not considered climate changes and other meteorological aspects to get proper results. 

Humidity and Event predictions are not accurate and require significant improvement in those approaches. Externalization of timeslots with location specific data is required to improve the temperature calculation model. Other factors like global warming, deforestation, urbanization can be added to those externalized rules to give improvement to the approach. Current season’s average values for temperature and humidity can be considered to get more reasonable results.

Other Approaches
================
•	Machine learning techniques like multiple linear regressions can be used on top of rich historical data to get more accurate results. 

•	Numerical weather prediction models used widely these days which take the current climatic conditions into account to predict weather data.

Technology Stack
================

Java 1.7

Maven

JUnit



Usage Instructions
==================

1. Build using maven (mvn package)

2. Run program as (cd target/ & java -jar JAR_NAME)




	







