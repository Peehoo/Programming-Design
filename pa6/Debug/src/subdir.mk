################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
O_SRCS += \
../src/ectest.o \
../src/listFuncs.o 

CPP_SRCS += \
../src/ectest.cpp \
../src/listFuncs.cpp 

OBJS += \
./src/ectest.o \
./src/listFuncs.o 

CPP_DEPS += \
./src/ectest.d \
./src/listFuncs.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


