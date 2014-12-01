library(RCurl)
library(XML)
wikiUrl  <- "http://en.wikipedia.org/wiki/List_of_states_and_territories_of_the_United_States"
dataTables <- readHTMLTable(wikiUrl)
statePopulation <- dataTables[[1]]
statePopulation[,6] <- gsub(",","",statePopulation[,6])
statePopulation[,6] <- as.numeric(statePopulation[,6])
names(statePopulation)[6] <- "Population"
statePopulation <- statePopulation[order(-statePopulation$Population),]
top20<- statePopulation[1:20,c(2,6)]
write.csv(top20,"top.csv")

states <-top20[,1]
for (state in states){
	stateUrl<-paste("http://data.stackexchange.com/stackoverflow/csv/326410?state=",state,sep="")
	download.file(stateUrl,paste(state,".csv",sep=""),method = "curl")
}
