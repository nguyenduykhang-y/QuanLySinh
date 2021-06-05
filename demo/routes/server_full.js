var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var mongodb = require('mongodb');

var MongoClient = mongodb.MongoClient;
var url = 'mongodb://localhost:27017/sinhvien';


MongoClient.connect(url, function(err, client) {
    if (err) {
        console.log('Unable to connect to the mongoDB server. Error:', err);
    } else {
        //HURRAY!! We are connected. :)
        let db = client.db('sinhvien');
        console.log('Connection established to', url);
        collection = db.collection('users');
        collection_sach = db.collection('sach');
    }
});


app.get('/', function(req, res) {
    res.sendfile('adad.html');
});


io.on('connection', function(socket) {
    socket.on('login', function(email, password) {
        console.log(email + "login");

        var cursor = collection.find({ email: email });
        cursor.each(function(err, doc) {
            if (err) {
                console.log(err);
                socket.emit('login', false);
            } else {
                if (doc != null) {
                    if (doc.password == password) {
                        socket.emit('login', true);
                    } else {
                        socket.emit('login', false);
                    }

                }
            }
        });

    });

   

    socket.on('getSach', function(msg) {
        console.log("Nhan lenh getStudent: " + msg);

        var cursor = collection_nhanvien.find();

        cursor.each(function(err, doc) {
            if (doc != null) {
                var strings = JSON.parse(JSON.stringify(doc));
                console.log(strings);
                socket.emit('getSach', strings)
            } else if (doc == null) {
                console.log("Ket thuc getSach");
            }
        });
    });

    socket.on('addSach', function(id, ten, theloai, tacgia, nxb) {
        console.log(ten + " addSach");

        var pro = {id: id, ten: ten, theloai: theloai, tacgia: tacgia, nxb: nxb };

        collection_sach.insert(pro, function(err, result) {
            if (err) {
                console.log(err);
                socket.emit('addSach', false);
            } else {
                console.log('Add new student ok');
                socket.emit('addSach', true);
            }
        });
    });

   

});

http.listen(3002, function() {
    console.log('listening on *:3002');
});