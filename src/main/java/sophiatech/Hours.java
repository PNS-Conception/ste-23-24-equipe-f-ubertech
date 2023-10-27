package sophiatech;

import java.util.Date;

public class Hours {

    private Date date_start;
    private Date date_end;

    public Hours(Date start, Date end){
        this.date_start = start;
        this.date_end = end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Hours)) return false;
        return this.date_start.equals(((Hours) obj).date_start) && this.date_end.equals(((Hours) obj).date_end);
    }
}
