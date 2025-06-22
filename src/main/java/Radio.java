public class Radio {
    private int currentStation;
    private int currentVolume;
    private int stationsCount;

    public Radio() {
        this.stationsCount = 10;
    }

    public Radio(int stationsCount) {
        if (stationsCount < 1) {
            this.stationsCount = 10;
        } else {
            this.stationsCount = stationsCount;
        }
    }

    public void next() {
        if (currentStation == stationsCount - 1) { // Переключение на следующую станцию (++)
            currentStation = 0;
        } else {
            currentStation++;
        }
    }

    public void prev() { // Переключение на предыдущую станцию (--)
        if (currentStation == 0) {
            currentStation = stationsCount - 1;
        } else {
            currentStation--;
        }
    }

    public void setCurrentStation(int currentStation) { // проверяем корректность номера радиостанции при её установке
        if (currentStation < 0 || currentStation >= stationsCount) { // если хотябы одно из условий верное то детод завершается без изменения станций
            return;
        }
        this.currentStation = currentStation;
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

    public int getCurrentStation() { // геттеры для станции
        return currentStation;
    }

    public int getCurrentVolume() { // геттеры для громкости
        return currentVolume;
    }

    public int getStationsCount() { // геттеры для номера станций
        return stationsCount;
    }
}
