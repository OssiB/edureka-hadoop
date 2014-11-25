wikiUrl  <- "http://en.wikipedia.org/wiki/List_of_U.S._states_and_territories_by_population"
dataTables <- readHTMLTable(wikiUrl)
statePopulation <- tables[4]
top20  <-  data[c(1:21),c(3,4)]
top20[,2] <- gsub(",","",top20[,2])
top20[,2] <- as.numeric(top20[,2]))
write.csv(top20,"top.csv")