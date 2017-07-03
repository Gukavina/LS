import java.io.*;
import java.text.*;
import java.util.*;

public class File_Manager {
    // Приватные члены класса
    private static boolean    flagL = false;
    private static boolean    flagR = false;
    private static boolean    flagH = false;
    private static boolean    flagO = false;
    private static int  counterFlag = 0;
    private static int  numberFlagO;
    private static int  counterItem = 1;
    private static fileAtribut[ ] fileAtributs = null;
    private static String  fileLogName = "fileInfo.txt";
    private static String  fileAnalizName = "fileAnaliz.txt";
    private static String  fileHelp = "java testApp [-l] [-h] [-r] [-0 [имя_файла_с_информацией]] имя_файла\\директории \n";
    private static String  fileInfo = "File info\n";


    // Точка входа
    // Точка входа
    public static void main( String[ ] args ) throws Exception {
        // Вывести в консоль результат анализа
        System.out.print(createInfoString(args ));
    }

    // Метод формирования информационной строки
    public static String createInfoString(String[ ] args ) throws Exception {
        // Локальные переменные
        String stringInfo = "";
        int i;						// Переменная цикла
        String stringTmp;			// Строка для временного хранения аргументов
        numberFlagO = args.length;	// Начальное значение переменной numberFlagO

        // Анализ параметров командной строки
        for( i = 0; i < args.length; i++)	{
            stringTmp = args[i];
            switch( stringTmp ) {
                case "-l":
                    flagL = true;
                    counterFlag++;
                    break;
                case "-h":
                    flagH = true;
                    counterFlag++;
                    break;
                case "-o":
                    flagO = true;
                    counterFlag++;
                    numberFlagO = i;
                    break;
                case "-r":
                    flagR = true;
                    counterFlag++;
                    break;
            };
        }

        // Анализ файла/директории
        if(args.length - counterFlag > 0) {
            fileAnalizName = args[args.length-1];	// Определить имя файла/директории
            readInfoFile();												// Считать информацию о файле/директории

            // Создание информационной строки
            stringInfoFile();

            // Определяем имя файла для записи информации
            if( (numberFlagO < args.length-2)&&(args.length > 2)&&(args[numberFlagO+1].charAt(0) != '-') ) fileLogName = args[numberFlagO+1];

            // Создание файла с информацией
            if( flagO )	writeLogFile();

            // Вывод информации о файле/директории
            stringInfo = fileInfo;

        } else stringInfo = fileHelp;		// Вывести справочную информацию

        // Вернуть информационную строку
        return stringInfo;
    }


    // Метод определение информации о файле.директории
    private static void readInfoFile() {
        File dir = new File(fileAnalizName);

        if(dir.exists()) {
            if(dir.isDirectory() ) counterItem = dir.listFiles().length;
            fileAtributs = new fileAtribut[counterItem];
            if(dir.isDirectory() ) {
                File[ ] item =  dir.listFiles() ;
                for(int i = 0; i < counterItem; i++ )
                    if( item[i].isDirectory())
                        fileAtributs[i]	= new fileAtribut(true,  item[i].canExecute(), item[i].canRead(),
                                item[i].canWrite(), item[i].getName(), item[i].length(), item[i].lastModified());
                    else fileAtributs[i] = new fileAtribut(false, item[i].canExecute(), item[i].canRead(),
                            item[i].canWrite(), item[i].getName(), item[i].length(), item[i].lastModified());
            } else fileAtributs[0] = new fileAtribut(false, dir.canExecute(), dir.canRead(), dir.canWrite(),
                    dir.getName(), dir.length(), dir.lastModified());
        }
    }

    // Метод формирования информационной строки
    private static void stringInfoFile() {
        if(fileAtributs == null) {
            fileInfo = "Входной параметр '"+fileAnalizName+"' не файл и не директория\n";
        } else {
            // Объект для форматирования времени и даты
            SimpleDateFormat formatDate = new SimpleDateFormat("Изменён в E dd.MM.yyyy' в 'HH:mm:ss' по 'zzz");
            fileInfo = "";
            String stringTmp = "";
            Date date;
            bubbleSortDir();
            // Формирование информационной строки
            for(int i = 0; i < counterItem; i++) {
                // Определить время модификации
                date = new Date( fileAtributs[i].timeModif);
                if (flagH)	{
                    stringTmp   = "Атрибуты: " + String.format("%-5s", fileAtributs[i].bitMaskH);
                    stringTmp += "Размер: "      + String.format("%5s%-8s", fileAtributs[i].sizeH, fileAtributs[i].sizeTypeH);
                    stringTmp +=  formatDate.format(date);
                    if(fileAtributs[i].flagDir)	stringTmp += String.format("%12s", "Каталог: ");
                    else stringTmp += String.format("%12s", "Файл: ");
                } else
                    stringTmp = fileAtributs[i].bitMaskB+ " " + fileAtributs[i].size + fileAtributs[i].sizeType + date + " ";
                if(flagL)	fileInfo +=  stringTmp + fileAtributs[i].name + "\n";
                else fileInfo +=   fileAtributs[i].name + "\n";
            }
        }
    }

    // Метод сортировки
    private static void bubbleSortDir( ) {
        fileAtribut fileAtributsTmp = null;
        boolean flagTmp;
        for(int i = counterItem-1 ;  i > 0 ;  i--)  {
            for(int j = 0 ; j < i ; j++) {
                // Попарное сравнение
                if(flagR)	flagTmp = fileAtributs[j].flagDir&& !fileAtributs[j+1].flagDir;
                else		flagTmp = !fileAtributs[j].flagDir&& fileAtributs[j+1].flagDir;
                if( flagTmp ) {
                    fileAtributsTmp	=	fileAtributs[j];
                    fileAtributs[j]			= 	fileAtributs[j+1];
                    fileAtributs[j+1]		=	fileAtributsTmp;
                }
            }
        }
    }

    // Метод записи в файл
    private static void writeLogFile() throws Exception {
        // Определяем файл для записи
        FileWriter logFile = new FileWriter( fileLogName );
        // Записать данные в файл
        logFile.write(fileInfo);
        logFile.close();
    }

}
