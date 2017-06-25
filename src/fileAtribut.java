
public class fileAtribut {
    // Атрибуты файла
    public boolean flagDir;
    public boolean flagEx;
    public boolean flagRd;
    public boolean flagWr;
    public String name;
    public long size;
    public double sizeH;
    public String sizeType;
    public String sizeTypeH;
    public long timeModif;
    public String bitMaskB;
    public String bitMaskH;
    // Базовый конструктор класса
    fileAtribut() {
        this.flagDir	= false;
        this.flagEx		= false;
        this.flagRd		= false;
        this.flagWr		= false;
        this.name		= "";
        this.size		= 0;
        this.sizeH		= 0;
        this.sizeType	= " Байт ";
        this.timeModif	= 0;
        this.bitMaskB 	= "000";
        this.bitMaskH	= "---";
    }

    fileAtribut( boolean flagDir, boolean flagEx, boolean flagRd, boolean flagWr, String name, long size,  long timeModif ) {
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
        if(size < 1024) {
            sizeH = size;
            sizeTypeH = " Байт ";
        }
        else if (size < 1024 * 1024) {
            sizeH = size / 1024;
            sizeTypeH		= " КБайт ";
        } else if (size < 1024 * 1024 *1024) {
            sizeH = size / 1024 / 1024;
            sizeTypeH		= " МБайт ";
        } else if (size < 1024 * 1024 * 1024 * 1024) {
            sizeH = size / 1024 / 1024 / 1024;
            sizeTypeH		= " КБайт ";
        }
    }

    // Вывод тестовой информации
    public void Info() {
        System.out.printf("Тип %s  Имя %s  Размер  %i  Изменён %i \n", flagDir, name, size, timeModif);
    }
}
