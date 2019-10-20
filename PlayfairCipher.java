
package sherby;

import java.util.*;


public class PlayfairCipher {
    


    private String key;
    private List<Character> letterList = new ArrayList<>();

    PlayfairCipher() {
        key = "security";
        generateTable();
    }

    PlayfairCipher(String key) {
        this.key = key;
        generateTable();
    }

   
    public String encrypt(String message) {

        StringBuilder stringBuilder = new StringBuilder();

        List<String> dividedMessagesList = divideMessage(message);

        for (String dividedMessage : dividedMessagesList) {
            if (dividedMessage.length() == 2) {
                String encryptedDividedMessage = encryptTwoCharacters(dividedMessage.charAt(0), dividedMessage.charAt(1));
                stringBuilder.append(encryptedDividedMessage);
            } else {
                stringBuilder.append(dividedMessage);
            }
        }
        return stringBuilder.toString();
    }

    
    public String decrypt(String encryptedMessage) {

        StringBuilder stringBuilder = new StringBuilder();

        List<String> messagesList = divideMessage(encryptedMessage);

        for (String dividedMessage : messagesList) {
            if (dividedMessage.length() == 2) {
                String decryptedDividedMessage = decryptTwoCharacters(dividedMessage.charAt(0), dividedMessage.charAt(1));
                stringBuilder.append(decryptedDividedMessage);
            } else {
                stringBuilder.append(dividedMessage);
            }
        }

        return stringBuilder.toString();
    }

    private void generateTable() {
        Set<Character> letterSet = new LinkedHashSet<>();
        for (char ch : key.toCharArray()) {
            if (ch == 'j')
                continue;
            letterSet.add(ch);
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (ch == 'j')
                continue;
            letterSet.add(ch);
        }

        letterList.addAll(letterSet);
    }

    private ArrayList<String> divideMessage(String message) {
        ArrayList<String> divideMessages = new ArrayList<>();

        for (int messageIndex = 0; messageIndex < message.length(); messageIndex += 2) {

            char char1 = message.charAt(messageIndex);
            while (!Character.isLowerCase(char1) || char1 == 'j') {
                divideMessages.add(String.valueOf(char1));
                messageIndex++;

                if (messageIndex < message.length())
                    char1 = message.charAt(messageIndex);
                else
                    break;
            }

            char char2;
            if (messageIndex + 1 < message.length()) {
                char2 = message.charAt(messageIndex + 1);
            }else {
                divideMessages.add(String.valueOf(char1));
                break;
            }

            while (!Character.isLowerCase(char2) || char2 == 'j') {
                divideMessages.add(String.valueOf(char2));
                messageIndex++;

                if (messageIndex + 1 < message.length())
                    char2 = message.charAt(messageIndex + 1);
            }

            divideMessages.add(char1 + "" + char2);

        }

        return divideMessages;
    }

    private String encryptTwoCharacters(char ch1, char ch2) {
        String encryptedString;

        if (isTheSameRow(ch1, ch2)) {
            encryptedString = rightCharter(ch1) + "" + rightCharter(ch2);
        } else if (isTheSameColumn(ch1, ch2)) {
            encryptedString = belowCharacter(ch1) + "" + belowCharacter(ch2);
        } else {
            encryptedString = getCornerCharacters(ch1, ch2);
        }
        return encryptedString;
    }

    private String decryptTwoCharacters(char char1, char char2) {
        String decryptedString;

        if (isTheSameRow(char1, char2)) {
            decryptedString = leftCharter(char1) + "" + leftCharter(char2);
        } else if (isTheSameColumn(char1, char2)) {
            decryptedString = upperCharacter(char1) + "" + upperCharacter(char2);
        } else {
            decryptedString = getCornerCharacters(char1, char2);
        }

        return decryptedString;
    }

    private String getCornerCharacters(char char1, char char2) {
        int newIndex1, newIndex2;
        int column1 = columnOfCharacter(char1);
        int column2 = columnOfCharacter(char2);

        if (column1 > column2) {
            newIndex1 = letterList.indexOf(char1) - (column1 - column2);
            newIndex2 = letterList.indexOf(char2) + (column1 - column2);
        } else {
            newIndex1 = letterList.indexOf(char1) + (column2 - column1);
            newIndex2 = letterList.indexOf(char2) - (column2 - column1);
        }
        return letterList.get(newIndex1) + "" + letterList.get(newIndex2);
    }

    private boolean isTheSameRow(char a, char b) {
        int rowA = rowOfCharacter(a);
        int rowB = rowOfCharacter(b);
        return rowA == rowB;
    }

    private boolean isTheSameColumn(char a, char b) {
        int columnA = columnOfCharacter(a);
        int columnB = columnOfCharacter(b);
        return columnA == columnB;
    }

    private char belowCharacter(char ch) {
        int belowIndex = (letterList.indexOf(ch) + 5) % 25;
        return letterList.get(belowIndex);
    }

    private char upperCharacter(char ch) {
        int upperIndex = (letterList.indexOf(ch) - 5 + 25) % 25;
        return letterList.get(upperIndex);
    }

    private char rightCharter(char ch) {
        int newCharColumn = (letterList.indexOf(ch) + 1) % 5;
        int rowIndex = rowOfCharacter(ch) * 5;
        int rightIndex = rowIndex + newCharColumn;

        return letterList.get(rightIndex);
    }

    private char leftCharter(char ch) {
        int newCharColumn = (letterList.indexOf(ch) - 1 + 5) % 5;
        int rowIndex = rowOfCharacter(ch) * 5;
        int leftIndex = rowIndex + newCharColumn;

        return letterList.get(leftIndex);
    }

    private int rowOfCharacter(char ch) {
        return letterList.indexOf(ch) / 5;
    }

    private int columnOfCharacter(char ch) {
        return letterList.indexOf(ch) % 5;
    }



}
    

