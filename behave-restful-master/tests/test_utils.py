import os
import sys
import unittest

from assertpy import assert_that

import behave_restful._utils as br_utils


class TestAddSearchPath(unittest.TestCase):

    def setUp(self):
        self.existing_path = os.path.abspath(os.path.dirname(__file__))
        self.not_existing_path = 'non_existing'

    def tearDown(self):
        if self.existing_path in sys.path:
            sys.path.remove(self.existing_path)

    def test_adds_path_if_exists(self):
        br_utils.add_search_path(self.existing_path)
        assert_that(self.existing_path in sys.path).is_true()

    def test_does_not_add_path_if_it_does_not_exist(self):
        br_utils.add_search_path(self.not_existing_path)
        assert_that(self.not_existing_path in sys.path).is_false()


if __name__ == "__main__":
    unittest.main()
