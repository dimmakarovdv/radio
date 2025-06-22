import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RadioTest {

    @Test
        // Тесты для радиостанций
    void shouldSetValidStation() {
        Radio radio = new Radio();
        radio.setCurrentStation(1);
        int expected = 1;
        int actual = radio.getCurrentStation();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldNotSetStationBelowMin() {
        Radio radio = new Radio();
        radio.setCurrentStation(-1);
        int expected = 0;
        int actual = radio.getCurrentStation();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldNotSetStationAboveMax() {
        Radio radio = new Radio();
        radio.setCurrentStation(10);
        int expected = 0;
        int actual = radio.getCurrentStation();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSwitchFromMaxToMinStation() {
        Radio radio = new Radio();
        radio.setCurrentStation(9);
        radio.next();
        int expected = 0;
        int actual = radio.getCurrentStation();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSwitchToNextStation() {
        Radio radio = new Radio();
        radio.setCurrentStation(5);
        radio.next();
        int expected = 6;
        int actual = radio.getCurrentStation();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSwitchFromMinToMaxStation() {
        Radio radio = new Radio();
        radio.setCurrentStation(0);
        radio.prev();
        int expected = 9;
        int actual = radio.getCurrentStation();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldSwitchToPrevStation() {
        Radio radio = new Radio();
        radio.setCurrentStation(5);
        radio.prev();
        int expected = 4;
        int actual = radio.getCurrentStation();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldIncreaseVolume() {
        Radio radio = new Radio();
        radio.increaseVolume();
        int expected = 1;
        int actual = radio.getCurrentVolume();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldNotIncreaseVolumeAboveMax() {
        Radio radio = new Radio();
        for (int i = 0; i < 100; i++) {
            radio.increaseVolume();
        }
        radio.increaseVolume();
        int expected = 100;
        int actual = radio.getCurrentVolume();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldDecreaseVolume() {
        Radio radio = new Radio();
        radio.increaseVolume(); // громкость на 1
        radio.decreaseVolume(); // уменьшаем громкость на -1
        int expected = 0;
        int actual = radio.getCurrentVolume();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldNotDecreaseVolumeBelowMin() {
        Radio radio = new Radio();
        radio.decreaseVolume(); // уменьшаем громкость меньше минимального (0)
        int expected = 0;
        int actual = radio.getCurrentVolume();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldHandleVolumeBoundaries() { // попытка установить уровень громкости выше мак значения
        Radio radio = new Radio();
        for (int i = 0; i < 100; i++) radio.increaseVolume();
        int expected = 100;
        int actual = radio.getCurrentVolume();
        Assertions.assertEquals(expected, actual);

        for (int i = 0; i < 100; i++) radio.decreaseVolume(); // уровень громкости на минимуме
        expected = 0;
        Assertions.assertEquals(expected, radio.getCurrentVolume());
    }

    @Test
    void shouldHandleStationBoundaries() {
        Radio radio = new Radio();

        // Проверка перехода от 9 до 0
        radio.setCurrentStation(9);
        radio.next();
        Assertions.assertEquals(0, radio.getCurrentStation());

        // Проверка перехода от 0 до 9
        radio.setCurrentStation(0);
        radio.prev();
        Assertions.assertEquals(9, radio.getCurrentStation());

        // Проверка валидных данных
        radio.setCurrentStation(0);
        Assertions.assertEquals(0, radio.getCurrentStation());

        radio.setCurrentStation(1);
        Assertions.assertEquals(1, radio.getCurrentStation());

        radio.setCurrentStation(9);
        Assertions.assertEquals(9, radio.getCurrentStation());

        radio.setCurrentStation(8);
        Assertions.assertEquals(8, radio.getCurrentStation());

        // Проверка невалидных данных
        radio.setCurrentStation(5);
        radio.setCurrentStation(-1);
        Assertions.assertEquals(5, radio.getCurrentStation()); // Отрицательное число присвоится не должно

        radio.setCurrentStation(10);
        Assertions.assertEquals(5, radio.getCurrentStation());
    }
    @Test
    void shouldCreateRadioWithDefaultStations() {
        Radio radio = new Radio();
        Assertions.assertEquals(10, radio.getStationsCount());
    }

    @Test
    void shouldCreateRadioWithCustomStations() {
        Radio radio = new Radio(15);
        Assertions.assertEquals(15, radio.getStationsCount());
    }

    @Test
    void shouldCreateRadioWithInvalidStations() {
        Radio radio = new Radio(-1);
        Assertions.assertEquals(10, radio.getStationsCount());
    }

    @Test
    void shouldSwitchFromMaxCustomStation() {
        Radio radio = new Radio(20);
        radio.setCurrentStation(19);
        radio.next();
        Assertions.assertEquals(0, radio.getCurrentStation());
    }

    @Test
    void shouldSwitchFromMinCustomStation() {
        Radio radio = new Radio(20);
        radio.setCurrentStation(0);
        radio.prev();
        Assertions.assertEquals(19, radio.getCurrentStation());
    }

    @Test
    void shouldNotSetInvalidStationForCustomRadio() {
        Radio radio = new Radio(12);
        radio.setCurrentStation(10);
        int saved = radio.getCurrentStation();

        radio.setCurrentStation(-1);
        Assertions.assertEquals(saved, radio.getCurrentStation());

        radio.setCurrentStation(12);
        Assertions.assertEquals(saved, radio.getCurrentStation());
    }

    @Test
    void shouldHandleVolumeAtMax() {
        Radio radio = new Radio();
        for (int i = 0; i < 101; i++) {
            radio.increaseVolume();
        }
        Assertions.assertEquals(100, radio.getCurrentVolume());
    }

    @Test
    void shouldHandleVolumeAtMin() {
        Radio radio = new Radio();
        for (int i = 0; i < 10; i++) {
            radio.decreaseVolume();
        }
        Assertions.assertEquals(0, radio.getCurrentVolume());
    }
}