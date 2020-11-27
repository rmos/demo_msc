docker stop $(docker ps -a |grep demo-msc|awk '{print $1;}')
docker rm $(docker ps -a |grep demo-msc|awk '{print $1;}')


docker build --tag demo-msc:v1 .
docker run -itd --network=curso-micros-mysql --name dm-msc -dit --restart always -p 8888:8888 demo-msc:v1
