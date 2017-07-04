/**
 * Created by Александра on 03;.07.2017.
 */

import org.junit.Test;

import static org.junit.Assert.*;



public class tests {
    String[ ] temp1 = {"-l", "-h", "c:\\users\\Александра\\documents\\projects\\java\\LS\\files_and_dir_for_tests"};
    String testfile_for_files_and_dir_for_tests = "Атрибуты: rwx  Размер:     4КБайт   Изменён в Вт 04.07.2017 в 02:44:06 по MSK   Каталог: dir1\n" +
    "Атрибуты: rwx  Размер:     0Байт    Изменён в Пн 03.07.2017 в 14:25:53 по MSK   Каталог: dir2\n" +
    "Атрибуты: rwx  Размер:     0Байт    Изменён в Пн 03.07.2017 в 14:26:03 по MSK   Каталог: dir3\n" +
    "Атрибуты: rwx  Размер:     0Байт    Изменён в Пн 03.07.2017 в 14:24:36 по MSK      Файл: рис1.bmp\n" +
    "Атрибуты: rwx  Размер:     0Байт    Изменён в Пн 03.07.2017 в 14:24:52 по MSK      Файл: рис2.bmp\n" +
    "Атрибуты: rwx  Размер:     0Байт    Изменён в Пн 03.07.2017 в 14:24:58 по MSK      Файл: текст1.txt\n" +
    "Атрибуты: rwx  Размер:     0Байт    Изменён в Пн 03.07.2017 в 14:25:13 по MSK      Файл: текст2.txt\n"
    ;

    String[ ] temp2 = {"-l",  "c:\\users\\Александра\\documents\\projects\\java\\LS\\files_and_dir_for_tests\\текст1.txt"};
    String testfile_for_text1 = "111 0 Байт Mon Jul 03 14:24:58 MSK 2017 текст1.txt\n";

    String[ ] temp3 = {"-l", "-h", "c:\\users\\Александра\\documents\\projects\\java\\LS\\files_and_dir_for_tests\\dir1"};
    String testfile_for_dir1 = "Атрибуты: rwx  Размер:     0Байт    Изменён в Вт 04.07.2017 в 02:44:06 по MSK   Каталог: dir1_1\n" +
            "Атрибуты: rwx  Размер:     0Байт    Изменён в Пн 03.07.2017 в 14:26:43 по MSK   Каталог: dir1_2\n" +
            "Атрибуты: rwx  Размер:     0Байт    Изменён в Пн 03.07.2017 в 14:27:08 по MSK      Файл: dir1_рис1.bmp\n" +
            "Атрибуты: rwx  Размер:     0Байт    Изменён в Пн 03.07.2017 в 14:27:47 по MSK      Файл: dir1_рис2.bmp\n" +
            "Атрибуты: rwx  Размер:     0Байт    Изменён в Пн 03.07.2017 в 14:27:58 по MSK      Файл: dir1_текст1.txt\n";

    String[ ] temp4 = {"-l", "-h", "c:\\users\\Александра\\documents\\projects\\java\\LS\\files_and_dir_for_tests\\dir1\\dir1_1"};
    String testfile_for_dir1_1 = "Атрибуты: rwx  Размер:     0Байт    Изменён в Пн 03.07.2017 в 14:28:48 по MSK      Файл: dir1_1_рис1.bmp\n" +
            "Атрибуты: rwx  Размер:     0Байт    Изменён в Пн 03.07.2017 в 14:28:22 по MSK      Файл: dir1_1_текст1.txt\n";

    String[ ] temp5 = {"-l", "-h", "c:\\users\\Александра\\documents\\projects\\java\\LS\\files_and_dir_for_tests\\5"};
    String error_not_file_or_dir = "Входной параметр 'c:\\users\\Александра\\documents\\projects\\java\\LS\\files_and_dir_for_tests\\5' не файл и не директория\n";

    String[ ] temp6 = {"-l", "-h"};
    String error_args = "java testApp [-l] [-h] [-r] [-0 [имя_файла_с_информацией]] имя_файла\\директории \n";

    @Test
    public void test1() throws Exception {
        assertEquals(testfile_for_files_and_dir_for_tests, File_Manager.createInfoString(temp1));
    }

    @Test
    public void test2() throws Exception {
        assertEquals(testfile_for_text1, File_Manager.createInfoString(temp2));
    }

    @Test
    public void test3() throws Exception {
        assertEquals(testfile_for_dir1, File_Manager.createInfoString(temp3));
    }

    @Test
    public void test4() throws Exception {
        assertEquals(testfile_for_dir1_1, File_Manager.createInfoString(temp4));
    }

    @Test
    public void test5() throws Exception {
        assertEquals(error_not_file_or_dir, File_Manager.createInfoString(temp5));
    }

    @Test
    public void test6() throws Exception {
        assertEquals(error_args, File_Manager.createInfoString(temp6));
    }


}
