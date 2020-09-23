#!/bin/bash

#Crate docker container
docker run --name anaconda3 -i -t -p 8888:8888 continuumio/anaconda3 /bin/bash -c "/opt/conda/bin/jupyter notebook --ip='*' --port=8888 --no-browser --allow-root"

#Install xgboost
pip install -i https://pypi.tuna.tsinghua.edu.cn/simple xgboost
pip install -i https://pypi.tuna.tsinghua.edu.cn/simple --proxy="proxy.cmcc:8080" --default-timeout=360 xgboost
