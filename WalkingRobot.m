clear

r1=8.1;  %set the lengths of the linkages in cm
r2=3.1;
r3=10.1;
r4=9.2;
BE=6.5;
B=zeros(1,943); %allocate spaces for the variables
C=zeros(1,943);
Ax=zeros(1,943); 
Ay=zeros(1,943);  
Bx=zeros(1,943);  
By=zeros(1,943);
Cx=zeros(1,943);  
Cy=zeros(1,943);
Dx=zeros(1,943);  
Dy=zeros(1,943);
Ex=zeros(1,943);
Ey=zeros(1,943);
CwrtB=zeros(1,943);
BD=zeros(1,943); 
phi=zeros(1,943);
beta=zeros(1,943);
theta3=zeros(1,943);
w2=zeros(1,943);
w3=zeros(1,943);
w4=zeros(1,943);
VAx=zeros(1,943);
VAy=zeros(1,943);
VBx=zeros(1,943);  
VBy=zeros(1,943);
VCx=zeros(1,943);
VCy=zeros(1,943); 
VDx=zeros(1,943); 
VDy=zeros(1,943);
VEx=zeros(1,943);
VEy=zeros(1,943);
disp=0;
i=1;    %initiate the index of the arrays
for theta2=0:0.02:6*pi     %count from 0 to 6pi in increments of 0.02
    B(i) = r2*exp(1i*theta2);  %position of B in complex number 
    Bx(i)=real(B(i));  %the x coordinate of position B will be the real number of B
    By(i)=imag(B(i));     %the y coordinate of position B will be the imaginary number of B
    
    %finding theta 3 
    BD(i)=sqrt(r1^2+r2^2-2*r1*r2*cos(theta2)); 
    phi(i)=acos([r3^2+BD(i)^2-r4^2]/[2*r3*BD(i)]);
    beta(i)=asin([r2/BD(i)]*sin(theta2));
    theta3(i)=phi(i)-beta(i);
    
    %finding theta 4
    lo(i)=asin([r3/r4]*sin(phi(i)));
    theta4(i)=-(beta(i)+lo(i));
    
    
    CwrtB(i,1) = r3*exp(1i*theta3(i));  %position of C wrt B
    C(i) = B(i) + CwrtB(i,1);    %the position of C wrt to the origin is (position of B) + (position of C wrt B)
    Cx(i) = real(C(i));   %the x coordinate of position B
    Cy(i) = imag(C(i));   % the y coordinate of position B
    Dx(i)=r1*cos(0.4189);   %the x coordinate of position D
    Dy(i)=r1*sin(0.4189);   % the y coordinate of position D
    Ex(i)=Bx(i)+BE*cos(2*3.14-[2.32757109-theta3(i)]); %the x coordinate of position E
    Ey(i)=By(i)+BE*sin(2*3.14-[2.32757109-theta3(i)]); %the y coordinate of position E
   
    %angular velocities 
    w2(i)=3.1;  %the angular velocity from the gearbox is 3.1rad/s
    w3(i)=(r2/r3)*w2(i)*[(sin(theta4(i)-theta2))/(sin(theta3(i)-theta4(i)))];
    w4(i)=(r2/r4)*w2(i)*[(sin(theta2-theta3(i)))/(sin(theta4(i)-theta3(i)))];
    w5(i)=w3(i);  %since it's a rigid link, linkage3 and linkage5 should have the same angular velocity 

    
    % // linear velocity equationà
    VAx(i)=0;
    VAy(i)=0;
    VBx(i) = w2(i)*Bx(i);  % linear velocity v = w*r
    VBy(i) = w2(i)*By(i);
    VCx(i) = w3(i)*real(r3*exp(1i*theta3(i)));   % Vcx=w*CwrtBx
    VCy(i) = w3(i)*imag(r3*exp(1i*theta3(i)));  %Vcy=w*CwrtBy
    VDx(i)=0;
    VDy(i)=0;
    VEx(i)=w5(i)*BE*cos(2*3.14-[2.32757109-theta3(i)]);
    VEy(i)=w5(i)*BE*sin(2*3.14-[2.32757109-theta3(i)]);
    
    
    alpha2(i)=0;
    alpha3(i)=[-r2*alpha2(i)*sin(theta4(i)-theta2)+r2*w2(i).^2*cos(theta4(i)-theta2)+r3*w3(i).^2*cos(theta4(i)-theta3(i))-r4*w4(i).^2]/[r3*sin(theta4(i)-theta3(i))];
    alpha4(i)=[r2*alpha2(i)*sin(theta3(i)-theta2)-r2*w2(i).^2*cos(theta3(i)-theta2)+r4*w4(i).^2*cos(theta3(i)-theta4(i))-r3*w3(i).^2]/[r4*sin(theta3(i)-theta4(i))];
    
    vectw2=[0 0 w2(i)];   %collecting variables needed to calculate the acceleration into vector form
    vectw3=[0 0 w3(i)];
    vectw4=[0 0 w4(i)];
    vectalpha2=[0 0 alpha2(i)];
    vectalpha3=[0 0 alpha3(i)];
    vectalpha4=[0 0 alpha4(i)];
    vectVb=[VBx(i) VBy(i) 0];
    vectVc=[VCx(i) VCy(i) 0];
    vectVe=[VEx(i) VEy(i) 0];
    vectRba=[Bx(i) By(i) 0];
    vectRcd=[Cx(i)-Dx(i) Cy(i)-Dy(i) 0];
    vectReb=[Bx(i)-Ex(i) By(i)-Ey(i) 0];
    Abn=cross(vectw2,vectVb);   %normal acceleration of a point can be calculated by doing the cross product to the angular velocity and the velocity of the corresponding point
    Abt=cross(vectalpha2,vectRba);      %tangential acceleration of a point can be calculate by doing the cross produce to the angular acceleration and 
                                        %the displacement wrt the end point of the corresponding point
    Acn=cross(vectw4,vectVc);
    Act=cross(vectalpha4,vectRcd);
    Aen=cross(vectw3,vectVe);
    Aet=cross(vectalpha3,vectReb);
    
    if Ey(i)<-7 %calculates the displacement of the robot
        disp=disp+(Ex(i)-Ex(i-1));
    end
    i=i+1;
end

figure;
for i=1:943   %loop each columns of the position values one at a time- creating an animation
   plot([Ax(i) Bx(i) Ex(i) Cx(i) Bx(i) Cx(i) Dx(i) Ax(i)],[Ay(i) By(i) Ey(i) Cy(i) By(i) Cy(i) Dy(i) Ay(i)]);
   axis([-10 10 -10 10]);  % set x axis range from -10 to 10 and y axis range from -10 to 10
   pause (0.0002);
end 


positions=[Ax;Ay;Bx;By;Cx;Cy;Dx;Dy;Ex;Ey];   %put all the location of the points into an array 
velocitys=[VAx;VAy;VBx;VBy;VCx;VCy;VDx;VDy;VEx;VEy]; %put all the calculated veclocities into an array
angularvelocitys=[w2;w3;w4;w5]; %put all the calculated angular veclocities into an array
array2table(positions,'RowNames',{'Ax','Ay','Bx','By','Cx','Cy','Dx','Dy','Ex','Ey'})   %printout all the datas in a table
array2table(velocitys,'RowNames',{'VAx','VAy','VBx','VBy','VCx','VCy','VDx','VDy','VEx','VEy'})
array2table(angularvelocitys,'RowNames',{'w2','w3','w4','w5'})

Accelerations=[Abn;Abt;Acn;Act;Aen;Aet];
angularAccelerations=[alpha2;alpha3;alpha4];
array2table(Accelerations,'RowNames',{'Abn','Abt','Acn','Act','Aen','Aet'})
array2table(angularAccelerations,'RowNames',{'alpha2','alpha3','alpha4'})
fprintf('the unit form position is in cm, velocity is in cm/s, angular velocity is in rad/s, accelerations is in cm/s^2, and angular acclerations in in rad/s^2');
fprintf('\nThe displacement of the robot is %f cm.',disp);