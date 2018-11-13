// js中创建类的方法
// ES6之前是通过构造函数的方式创建类
function Point (x , y) {
  this.x = x;
  this.y  =y;
}

// ES6之后可以通过class关键字来定义类
class Point {

  // 构造方法
  constructor(x,y){
    this.x = x;
    this.y = y;
  }

  // toString方法
  toString() {
    return '(' + this.x + ', ' + this.y + ')';
  }
}

typeof Point // function
Point === Point.prototype.constructor // true
// 类的数据类型就是函数，类本身就指向构造函数