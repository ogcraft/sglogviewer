#!/usr/bin/env python

from __future__ import print_function

import sys
from socket import *
from datetime import *
import struct
import time
import os
import difflib

PROTOBUF_FILES_DIR='../../../../../VisualStudioBuildProducts/sgring-protobuf'
sys.path.insert(0, PROTOBUF_FILES_DIR)

import ag_sgring_pb2 


def do_record(r):
    data = r.all_data
    print("host_items_size:", len(data.host_items))
    for item in data.host_items:
        print("======================")
        print("uuid:", item.uuid, "has", len(item.items), "db_item")
        for it in item.items:
            print("db_item key:", it.key,"type:",ag_sgring_pb2.db_value_type.Name(it.value.type))
            user_info = it.value.val_db_user_info
            print(user_info.soe_master_mac)

if __name__ == '__main__':

    A_dir="../../../../../Products/Debug/Apps/IOSharingA/"
    B_dir="../../../../../Products/Debug/Apps/IOSharingB/"
    
    dump_file = A_dir + "ring_bdl_dump.bin"
    print("Dump file: ", dump_file)
    v1 = 12
    v2 = 13
    records = []
    with open(dump_file, "rb") as data:
        while True:
            rec_type = data.read(4)
            if not rec_type:
                break
            rec_size = struct.unpack('i', data.read(4))[0]
            rec_buf = data.read(rec_size)
            rec = ag_sgring_pb2.db_dump_section()
            rec.ParseFromString(rec_buf)
            d = datetime.fromtimestamp(rec.timestamp/1000000.0)
            print("Rec:", d, "type:", rec_type, "size:", rec_size, "bdl_ver:", rec.bundles_version_num, "inv_ver:", rec.inventory_version_num, "own_ver:", rec.ownership_version_num)
            if rec.bundles_version_num in [v1,v2]:
                records.append(rec)

    print("Collected records: ", len(records))
    
    #s1 = str(records[0])
    #s2 = str(records[1])

    #diff = difflib.context_diff(s1.split('\n'), s2.split('\n'))
    #diff = difflib.ndiff(s1.split('\n'), s2.split('\n'))
    #for l in diff:
    #    print("[", l, "]")

    r = records[0]

    do_record(r)
    

