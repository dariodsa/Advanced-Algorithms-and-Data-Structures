#include "dialog.h"
#include "ui_dialog.h"
#include <qdebug.h>

Dialog::Dialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::Dialog)
{
    ui->setupUi(this);
    scene = new QGraphicsScene(this);

    ui->graphicsView->setScene(scene);



}

Dialog::~Dialog()
{
    delete ui;
}

void Dialog::on_pushButton_clicked()
{
    QString qString = QFileDialog::getOpenFileName(this,"Open", ".");
    qDebug() << qString;


}
