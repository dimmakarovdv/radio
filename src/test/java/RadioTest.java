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
    void shouldHandleVolumeBoundaries() { // уровень громкости на максимуме
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
}