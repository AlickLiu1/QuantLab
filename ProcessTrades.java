import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ProcessTrades {
    public static void main(String[] args) throws IOException{
        if(args.length != 1 && args.length != 2){
            throw new IllegalArgumentException("the input argument size is wrong");
        }
        int argLength = args.length;
        String inputFile = "";
        String outputFile = "";
        inputFile = args[0];
        if(argLength == 2){
           outputFile = args[1];
        }
        Map<String, List<Tuple>> dataSet = new  HashMap<>();
        Path currentRelativePath = Paths.get("");
        String HomeDirectory = currentRelativePath.toAbsolutePath().toString();
        String csvFile = HomeDirectory + "/" + inputFile;
        List<String> symbols = new ArrayList<>();
        buildMap(dataSet, csvFile, symbols);
        Collections.sort(symbols);
        List<String> outputs;
        outputs = getOutput(dataSet, symbols);
        if(argLength == 1){
            for(String output: outputs){
                System.out.println(output);
            }
        }
        else{
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            for(String output: outputs){
                writer.write(output);
                writer.newLine();
            }
            writer.close();
            System.out.println("The file is written to the " + outputFile);
        }
    }

    public static void buildMap(Map<String,List<Tuple>> dataSet, String csvFile,
                                List<String> symbols) {
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                if(data.length!=4){
                    throw new IllegalArgumentException("the provided csv file data size is wrong");
                }
                long timeStamp = Long.parseLong(data[0].trim());
                String symbol =  data[1].trim();
                int quantity = Integer.parseInt(data[2].trim());
                int price = Integer.parseInt(data[3].trim());
                Tuple tuple = new Tuple(timeStamp, symbol, quantity, price);

                if(!dataSet.containsKey(symbol)){
                    dataSet.put(symbol, new ArrayList<>());
                    symbols.add(symbol);
                }
                dataSet.get(symbol).add(tuple);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getOutput(Map<String,List<Tuple>> dataSet, List<String> symbols) throws IOException{
        List<String> outputs = new ArrayList();
        for(String symbol: symbols){
            List<Tuple> dataList = dataSet.get(symbol);
            int maxTradeValue = 0;
            int weightedAverageValue = 0;
            int weightedTotalValue = 0;
            int totalQuantity = 0;
            long maxTimeStampGap = 0;
            long prevTimeStamp = 0;


            for(int i = 0; i<dataList.size(); i++){
                Tuple tuple = dataList.get(i);
                long timeStamp = tuple.getTimeStamp();
                int quantity = tuple.getQuantity();
                int price = tuple.getPrice();
                if(i==0){
                    prevTimeStamp = timeStamp;
                }
                else{
                    maxTimeStampGap = Math.max(maxTimeStampGap, timeStamp - prevTimeStamp);
                    prevTimeStamp = timeStamp;
                }
                totalQuantity += quantity;
                weightedTotalValue += quantity * price;
                maxTradeValue = Math.max(maxTradeValue,price);
            }
            weightedAverageValue = weightedTotalValue / totalQuantity;
            StringBuilder sb = new StringBuilder();
            sb.append(symbol);
            sb.append(",");
            sb.append(maxTimeStampGap);
            sb.append(",");
            sb.append(totalQuantity);
            sb.append(",");
            sb.append(weightedAverageValue);
            sb.append(",");
            sb.append(maxTradeValue);
            outputs.add(sb.toString());
        }
        return outputs;
    }
}
