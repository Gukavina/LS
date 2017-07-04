import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class FileAtribut {
    // Атрибуты файла
    public boolean flagDir;
    public boolean flagEx;
    public boolean flagRd;
    public boolean flagWr;
    public String name;
    public long size;
    public BigDecimal sizeH;
    public String sizeType;
    public String sizeTypeH;
    public long timeModif;
    public String bitMaskB;
    public String bitMaskH;
    // Базовый конструктор класса
    FileAtribut() {
        this.flagDir	= false;
        this.flagEx		= false;
        this.flagRd		= false;
        this.flagWr		= false;
        this.name		= "";
        this.size		= 0;
        this.sizeH		= new BigDecimal(0.0);
        this.sizeType	= " Байт ";
        this.timeModif	= 0;
        this.bitMaskB 	= "000";
        this.bitMaskH	= "---";
    }

    FileAtribut( boolean flagDir, boolean flagEx, boolean flagRd, boolean flagWr, String name, long size,  long timeModif ) {
        this.flagDir    = flagDir;
        this.flagEx	    = flagEx;
        this.flagRd	    = flagRd;
        this.flagWr	    = flagWr;
        this.name       = name;
        this.size       = size;
        this.timeModif	= timeModif;
        this.sizeType   = " Байт ";
        sizeHum();
        // Определить битовую маску
        if(flagRd)	{
            bitMaskH = "r";
            bitMaskB = "1";
        } else {
            bitMaskH = "-";
            bitMaskB = "0";
        }
        if(flagWr)	{
            bitMaskH += "w";
            bitMaskB += "1";
        } else {
            bitMaskH += "-";
            bitMaskB += "0";
        }
        if(flagEx) {
            bitMaskH += "x";
            bitMaskB += "1";
        } else {
            bitMaskH += "-";
            bitMaskB += "0";
        }
    }

    // Определение удобочитаемого размера
    private void sizeHum() {
        sizeH = new BigDecimal(size);
        BigDecimal divisor = new BigDecimal(1024);

        int i = 0;
        while (sizeH.divide(divisor).compareTo(BigDecimal.ONE) > 0) {
            sizeH = sizeH.divide(divisor);
            i++;
        }

        switch (i) {
            case 0: sizeTypeH = "Байт";
                break;
            case 1: sizeTypeH = "КБайт";
                break;
            case 2: sizeTypeH = "МБайт";
                break;
            case 3: sizeTypeH = "Гбайт";
                break;
            case 4: sizeTypeH = "ТБайт";
                break;
            case 5: sizeTypeH = "Пбайт";
                break;

            default: sizeTypeH = "ЭБайт";
        }

        sizeH = sizeH.round(new MathContext(4, RoundingMode.HALF_UP));
        //if(size < 1024) {
        //    sizeH = size;
        //    sizeTypeH = " Байт ";
        //}
        //else if (size < 1024 * 1024) {
        //    sizeH = size / 1024;
        //    sizeTypeH		= " КБайт ";
        //} else if (size < 1024 * 1024 *1024) {
        //    sizeH = size / 1024 / 1024;
        //    sizeTypeH		= " МБайт ";
        //} else if (size < 1024 * 1024 * 1024 * 1024) {
        //    sizeH = size / 1024 / 1024 / 1024;
        //    sizeTypeH		= " КБайт ";
        //}
    }

    // Вывод тестовой информации
    public void Info() {
        System.out.printf("Тип %s  Имя %s  Размер  %i  Изменён %i \n", flagDir, name, size, timeModif);
    }
}
