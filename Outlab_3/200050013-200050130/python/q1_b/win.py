import argparse
ap = argparse.ArgumentParser()
ap.add_argument("--path1", required = True)
ap.add_argument("--path2", required = True)
ap.add_argument("--output", required = True)
args = vars(ap.parse_args())




f0 = open(args['path1'], "r")




for x in f0:
    y=x.split("/")
    if(y[2][-1]=="\n"):
        s=y[2][:-1]
    else:
        s=y[2]
    print(s)

f0.close()

f2 = open(args['output'] + "winners.txt", "w")
f2.close()
f1 = open(args['path2'], "r")
count=0
for x in f1:
    y=x.split("||")
    user_name = y[0]
    ip_address = y[2]
    if(y[3][-1]=="\n"):
        url=y[3][:-1]
    else:
        url=y[3]
    w=url.split("www.")
    if(len(w)==2):
        url = w[1]

    f0 = open(args['path1'], "r")
    for z in f0:
        url2 = z.split("www.")
        if url == url2[1][:-1]:
            print("user_name - "+user_name+" : Winner - Lucky draw!!! - "+z, end="")
            count+=1
            f2 = open(args['output'] + "winners.txt", "a")
            f2.write(user_name + "||" + ip_address + "||" + z)
            f2.close()
            
    f0.close()

print(count)
