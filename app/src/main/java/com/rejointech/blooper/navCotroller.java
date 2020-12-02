package com.rejointech.blooper;

public interface navCotroller {
    interface drawer

    {
        public void setDrawer_locked ();
        public void setDrawer_unlocked ();
    }
    interface toolController{
        public void setToolInvisible();
        public void setToolVisible();
        public void toolbuttonunclickable();
        public void toolbuttonunnotclickable();

    }



}

