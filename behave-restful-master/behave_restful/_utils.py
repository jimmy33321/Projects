"""
This module provides internal utility functions that are used by the framework
implementation and should not be needed by users of the framework.
"""
import importlib
import os.path
import sys


def add_search_path(*path_tokens):
    """
    Adds the specified directory to the Python search path.

    :param *args:
        List of arguments, as used with ``os.path.join()`` that compose the
        directory to be added.
    """
    full_path = os.path.join(*path_tokens)
    if full_path not in sys.path:
        sys.path.insert(0, os.path.abspath(full_path))



def load_module(module_name):
    """
    Dynamically imports the specified module and returns it.

    :param str module_name:
        Name of the module to be imported as a string. 
    """
    return importlib.import_module(module_name)
