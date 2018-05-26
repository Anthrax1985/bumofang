function OrdersList(){
    var self = this;
    self.data = {};
    self.event = {};

    //--------------------------
    //data
    //--------------------------


    //--------------------------
    //event
    //--------------------------
    self.bind_event = function(){
        $(document).off('click.add_order').on('click.add_order', '.add_order', self.onAddOrder);
    }

    self.onAddOrder = function () {
        layer.open({
            type:2,
            area:['900px', '550px'],
            fixed:false,
            maxmin:true,
            content:'orders/addPage.do'
        });
    }

    self.init = function(){
        self.bind_event();
    }
    self.init();
}


$(document).ready(function(){
    var obj = new OrdersList();
});