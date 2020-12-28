package devx.app.datalistapp.base

import devx.app.datalistapp.model.home.MyData

object DummyUtil {
    fun loadDummy(): MutableList<MyData> {
        var items: MutableList<MyData> = mutableListOf()
        items.add(
            MyData(
                "https://dummyimage.com/600x400/000/fff&text=1",
                "First data",
                "Custom text can be entered using a query string at the very end of the url.\n" +
                        "This is optional, default is the image dimensions "
            )
        )
        items.add(
            MyData(
                "https://dummyimage.com/600x400/000/fff&text=2",
                "Second data",
                "Text can be entered using a query string at the very end of the url.\n" +
                        "This is optional, default is the image dimensions "
            )
        )
        items.add(
            MyData(
                "https://dummyimage.com/600x400/000/fff&text=3",
                "Third data",
                "Can be entered using a query string at the very end of the url.\n" +
                        "This is optional, default is the image dimensions "
            )
        )
        return items
    }
}