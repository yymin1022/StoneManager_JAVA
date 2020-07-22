package Utils;

public class Log{
    final int LOG_TYPE_DEBUG = 0;
    final int LOG_TYPE_ERROR = 1;
    final int LOG_TYPE_INFO = 2;

    public Log(){

    }

    public void printLog(int type, String log){
        switch(type){
            case LOG_TYPE_DEBUG:
                System.out.println(String.format("DEBUG : %s", log));
                break;
            case LOG_TYPE_ERROR:
                System.out.println(String.format("ERROR : %s", log));
                break;
            case LOG_TYPE_INFO:
                System.out.println(String.format("INFO : %s", log));
                break;
        }
    }
}
