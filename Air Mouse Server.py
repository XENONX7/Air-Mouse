import socket
from pynput.mouse import Button,Controller


HOST = socket.gethostname()
IP_ADDRESS = socket.gethostbyname(HOST)
PORT =7000
s = socket.socket()
s.bind((IP_ADDRESS, PORT))
s.listen(1) 
print("Enter This ",IP_ADDRESS,"IP Address In Your Application")
while True:
    
    conn, addr = s.accept()
    print ("Connected by: " , addr)
    break

mouse=Controller()

while True:
   
    conn, addr = s.accept()
    data = conn.recv(1024) 
    
    PAD=list(str(repr(data)))
    PDAL=len(PAD)
    USI=PAD.index("_")
    PAD=PAD[USI+1:PDAL-1]
    MOUSE_CORD="".join(PAD)

    #Left Mouse Button
    if MOUSE_CORD=="BL":
        mouse.press(Button.left)
        mouse.release(Button.left)
    #Left Mouse Button Long Press
    elif MOUSE_CORD=="BLL":
        mouse.press(Button.left)
    #Right Mouse Button
    elif MOUSE_CORD=="BR":
        
        mouse.press(Button.right)
        mouse.release(Button.right)
    #Right Mouse Button Long Press
    elif MOUSE_CORD=="BRL":
        
        mouse.press(Button.right)

    elif MOUSE_CORD=="CONNECTED":
        print("Connection Already Estabilished")
        
    
    elif MOUSE_CORD=="DICONNECTED":
        print("Conection Ended - Re-Run to estabilish a new conection")
        break
        
        

    #Coordinates X,Y From Phone Accelerometer Sensor. Mapped to Mouse Movement. 
    else:  
        MOUSE_CORD_XY=MOUSE_CORD.split(":")
        X=int(str(MOUSE_CORD_XY[0]))
        Y=int(str(MOUSE_CORD_XY[1]))
        #print(X,Y)
        mouse.move(X,Y)

