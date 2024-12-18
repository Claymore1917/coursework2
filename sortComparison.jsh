ArrayList<Integer> cardCompare(String card1, String card2) {
    int number1 = Integer.parseInt(card1.substring(0, card1.length() - 1));
    int number2 = Integer.parseInt(card2.substring(0, card2.length() - 1));
    char suit1 = card1.charAt(card1.length() - 1);
    char suit2 = card2.charAt(card2.length() - 1);

    String suitPriority = "HCDS";


    if (suitPriority.indexOf(suit1) < suitPriority.indexOf(suit2)) {
        return new ArrayList<>(List.of(-1));
    } else if (suitPriority.indexOf(suit1) > suitPriority.indexOf(suit2)) {
        return new ArrayList<>(List.of(1));
    } else {

        if (number1 < number2) {
            return new ArrayList<>(List.of(-1));
        } else if (number1 > number2) {
            return new ArrayList<>(List.of(1));
        } else {
            return new ArrayList<>(List.of(0));
        }
    }
}


ArrayList<String> bubbleSort(ArrayList<String> array) {
    int n = array.size();
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (cardCompare(array.get(j), array.get(j + 1)).get(0) > 0) {
                String temp = array.get(j);
                array.set(j, array.get(j + 1));
                array.set(j + 1, temp);
            }
        }
    }
    return array;
}


ArrayList<String> mergeSort(ArrayList<String> array) {
    if (array.size() <= 1) {
        return array;
    }

    int mid = array.size() / 2;
    ArrayList<String> left = new ArrayList<String>(array.subList(0, mid));
    ArrayList<String> right = new ArrayList<String>(array.subList(mid, array.size()));

    left = mergeSort(left);
    right = mergeSort(right);

    return merge(left, right);
}

ArrayList<String> merge(ArrayList<String> left, ArrayList<String> right) {
    ArrayList<String> result = new ArrayList<String>();
    int i = 0, j = 0;

    while (i < left.size() && j < right.size()) {
        if (cardCompare(left.get(i), right.get(j)).get(0) <= 0) {
            result.add(left.get(i));
            i++;
        } else {
            result.add(right.get(j));
            j++;
        }
    }

    while (i < left.size()) {
        result.add(left.get(i));
        i++;
    }

    while (j < right.size()) {
        result.add(right.get(j));
        j++;
    }

    return result;
}


long measureBubbleSort(String filename) throws IOException {
    ArrayList<String> list = new ArrayList<String>();
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    String line;
    
    while ((line = reader.readLine()) != null) {
        list.add(line.trim());
    }
    reader.close();

    long startTime = System.currentTimeMillis();
    
    bubbleSort(list);
    
    long endTime = System.currentTimeMillis();
    

    return endTime - startTime;
}


long measureMergeSort(String filename) throws IOException {
    ArrayList<String> list = new ArrayList<String>();
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    String line;
    
    while ((line = reader.readLine()) != null) {
        list.add(line.trim());
    }
    reader.close();

    long startTime = System.currentTimeMillis();
    
    mergeSort(list);
    
    long endTime = System.currentTimeMillis();
    
    return endTime - startTime;
}



void sortComparison(String[] filenames) throws IOException {

    printCSVContents("sortComparison.csv");
}

void printCSVContents(String csvFilename) throws IOException {
    System.out.println("CSV Contents:");
    BufferedReader reader = new BufferedReader(new FileReader(csvFilename));
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
    reader.close();
}

