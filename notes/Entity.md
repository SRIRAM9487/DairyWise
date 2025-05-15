# ENTITY

## USER 
1. id
1. userName
1. password
1. role
1. email
1. isLocked
1. isEnabled
1. creationTime
1. updatedAt
1. lastLogin

## CUSTOMER
1. id
1. name
1. phoneNumber
1. accountNumber
1. creationTime
1. updatedAt
1. managerId(foreign key)

## DAILY ENTRY

1. id
1. quantity
1. shift
1. entryDate
1. creationTime
1. customerId(foreign key)
1. managerId(foreign key)


## REPORT METADATA
1. id
1. managerId
1. reportType(Daily/Monthly/Yearly)
1. status(Generated/Sent/Failed)
1. formDate
1. toDate
1. creationTime
1. filePathUrl
