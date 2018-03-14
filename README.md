在开发详情页时，我们需要实现这样一个下面这样的动画。



在看到这个动画设计的时候，我就想到“京东”和“淘宝”的加入购物车的动画。



话不多说，讲一下这个动画的实现原理：

1、获取“对比”按钮和“比+1”按钮的绝对位置

2、在ScrollView滑动过程中，这两个按钮的位置会不断变化，所以需要不断更新两个按钮的位置

scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                int[] location1 = new int[2] ;
                testView.getLocationInWindow(location1); //获取在当前窗口内的绝对坐标
                testX = location1[0]; testY = location1[1];
                int[] location2 = new int[2] ;
                testView02.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标
                test02X = location2[0]; test02Y = location2[1];
                Log.e("123", location1[0] + ", " + location1[1] + " onWindowFocusChanged: " + location2[0] + ", " + location2[1]);
            }
        });
3、在点击“对比”按钮时，执行动画——把位于“对比”按钮上的icon，移动到“比+1”按钮上；同时执行透明度动画。


总体的体会是，一些看似很复杂的动画，其实实现起来还是比较容易的。

在此附上demo地址（点击即可下载），欢迎star
