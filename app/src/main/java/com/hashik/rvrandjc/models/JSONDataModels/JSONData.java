package com.hashik.rvrandjc.models.JSONDataModels;

public class JSONData {
        private Attendancereport[] attendancereport;

        private String time;

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    private Internalmarks[] internalmarks;

        private Semester[] semester;

        private User user;

        public Attendancereport[] getAttendancereport ()
        {
            return attendancereport;
        }

        public void setAttendancereport (Attendancereport[] attendancereport)
        {
            this.attendancereport = attendancereport;
        }

        public Internalmarks[] getInternalmarks ()
        {
            return internalmarks;
        }

        public void setInternalmarks (Internalmarks[] internalmarks)
        {
            this.internalmarks = internalmarks;
        }

        public Semester[] getSemester ()
        {
            return semester;
        }

        public void setSemester (Semester[] semester)
        {
            this.semester = semester;
        }

        public User getUser ()
        {
            return user;
        }

        public void setUser (User user)
        {
            this.user = user;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [attendancereport = "+attendancereport+", internalmarks = "+internalmarks+", semester = "+semester+", user = "+user+"]";
        }
}
