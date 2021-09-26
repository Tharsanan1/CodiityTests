import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ArrangePhotos {
    public static void main(String[] args) {

    }

    private static class Photos implements Comparable<Photos>{
        String extension;
        String city;
        String time;
        String originalName;
        String generatedName;

        public Photos(String extension, String city, String time, String originalName) {
            this.extension = extension;
            this.city = city;
            this.time = time;
            this.originalName = originalName;
        }

        @Override
        public int compareTo(Photos photos) {
            return this.time.compareTo(photos.time);
        }
    }

    public String solution(String S) {
        String[] photos = S.split("\n");
        HashMap<String, ArrayList<Photos>> photosMap = new HashMap<>();
        ArrayList<String> photoListInOrder = new ArrayList<>();
        for (String photo : photos) {
            photoListInOrder.add(photo);
            String[] elements = photo.split(",");
            System.out.print("***\n***" + elements[0] + elements[0].trim().split("/.").length);
            String nameWithExtension = elements[0].trim();
            String extension = nameWithExtension.substring(nameWithExtension.indexOf(".")+1);
            System.out.print(extension);
            String city = elements[1].trim();
            String timeStamp = elements[2].trim()
                    .replace(" ", "")
                    .replace("-","")
                    .replace(":", "");

            if (photosMap.containsKey(city)) {
                photosMap.get(city).add(new Photos(extension, city, timeStamp, photo));
            } else {
                ArrayList<Photos> photosArr = new ArrayList<>();
                photosArr.add(new Photos(extension, city, timeStamp, photo));
                photosMap.put(city, photosArr);
            }
        }
        HashMap<String, String> nameMap = new HashMap<>();
        for (String key : photosMap.keySet()) {
            Collections.sort(photosMap.get(key));
            int size = photosMap.get(key).size();
            int lengthOfMaxSize = (int)(Math.log10(size)+1);
            for (int i = 0; i < photosMap.get(key).size(); i++) {
                String generatedName  = photosMap.get(key).get(i).city + String.format("%0" + lengthOfMaxSize + "d", i+1) +  "." + photosMap.get(key).get(i).extension;
                photosMap.get(key).get(i).generatedName = generatedName;
                nameMap.put(photosMap.get(key).get(i).originalName, photosMap.get(key).get(i).generatedName);
            }
        }
        StringBuilder result = new StringBuilder();
        for (String photoString : photoListInOrder) {
            if (nameMap.containsKey(photoString)) {
                result.append(nameMap.get(photoString));
                result.append("\n");
            }
        }
        String resultString = result.toString();
        if (resultString.length() > 0) {
            resultString = resultString.substring(0,resultString.length()-1);
        }
        return resultString;


    }
}
