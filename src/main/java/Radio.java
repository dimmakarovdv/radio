public class Radio {
    private int currentStation;
    private int currentVolume;

    public void next() {
        if (currentStation == 9) { // Переключение на следующую станцию (++)
            currentStation = 0;
        } else {
            currentStation++;
        }
    }

    public void prev() { // Переключение на предыдущую станцию (--)
        if (currentStation == 0) {
            currentStation = 9;
        } else {
            currentStation--;
        }
    }

    public void increaseVolume() { // увеличение громкости
        if (currentVolume < 100) {
            currentVolume++;
        }
    }

    public void decreaseVolume() { // уменьшение громкости
        if (currentVolume > 0) {
            currentVolume--;
        }
    }

    public int setCurrentStation(int station) { // установка радиостанции с проверкой
        if (station >= 0 && station <= 9) {
            currentStation = station;
        }
        return station;
    }

    public int getCurrentStation() { // геттеры для станции
        return currentStation;
    }

    public int getCurrentVolume() { // геттеры для громкости
        return currentVolume;
    }
}
